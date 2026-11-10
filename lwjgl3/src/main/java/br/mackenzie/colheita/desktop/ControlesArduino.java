package br.mackenzie.colheita.desktop;

import br.mackenzie.colheita.input.ControlesJogo;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;
import com.fazecast.jSerialComm.SerialPortTimeoutException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import java.util.regex.Pattern;

final class ControlesArduino implements ControlesJogo, AutoCloseable {
    private static final Pattern ESTADO = Pattern.compile(
        "Branco: (SOLTO|APERTADO) \\| Azul: (SOLTO|APERTADO)");
    private static final long VALIDADE_NS = 500_000_000L;
    private static final long LIMITE_SEM_LINHAS_NS = 5_000_000_000L;

    private record Leitura(boolean esquerda, boolean direita, long instante) {}
    private final String nomePorta;
    private final Thread leitor;
    private volatile boolean ativo = true;
    private volatile Leitura ultima;
    private volatile String situacao = "ABRINDO PORTA";
    private Leitura quadro;

    ControlesArduino(String nomePorta) {
        this.nomePorta = nomePorta;
        leitor = new Thread(this::ler, "arduino-serial");
        leitor.setDaemon(true);
        leitor.start();
    }

    static void prepararBibliotecaWindows() throws IOException {
        if (!System.getProperty("os.name", "").toLowerCase(Locale.ROOT).startsWith("windows")
            || !System.getProperty("jSerialComm.library.path", "").isEmpty()) return;
        String arquitetura = switch (System.getProperty("os.arch", "").toLowerCase(Locale.ROOT)) {
            case "amd64", "x86_64" -> "x86_64";
            case "aarch64", "arm64" -> "aarch64";
            case "x86", "i386", "i686" -> "x86";
            default -> throw new IOException("Arquitetura Java nao suportada no Windows.");
        };
        String recurso = "/Windows/" + arquitetura + "/jSerialComm.dll";
        try (var origem = ControlesArduino.class.getResourceAsStream(recurso)) {
            if (origem == null) throw new IOException("Biblioteca serial ausente: " + recurso);
            var pasta = Files.createTempDirectory("colheita-serial-");
            var biblioteca = pasta.resolve("jSerialComm.dll");
            pasta.toFile().deleteOnExit();
            Files.copy(origem, biblioteca);
            biblioteca.toFile().deleteOnExit();
            System.setProperty("jSerialComm.library.path", pasta.toAbsolutePath().toString());
            System.setProperty("fazecast.jSerialComm.appid", pasta.getFileName().toString());
        }
    }

    private void ler() {
        try {
            prepararBibliotecaWindows();
        } catch (IOException e) {
            situacao = "FALHA NA BIBLIOTECA SERIAL";
            e.printStackTrace();
            return;
        }
        while (ativo) {
            SerialPort porta = null;
            try {
                porta = SerialPort.getCommPort(nomePorta);
                porta.setComPortParameters(9600, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
                porta.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 750, 0);
                if (porta.openPort()) {
                    situacao = "PORTA ABERTA, AGUARDANDO DADOS";
                    lerEstados(porta);
                } else {
                    situacao = "PORTA INDISPONIVEL";
                }
            } catch (IOException | SerialPortInvalidPortException | IllegalArgumentException | IllegalStateException e) {
                situacao = "PORTA INDISPONIVEL";
            } catch (LinkageError e) {
                situacao = "FALHA NA BIBLIOTECA SERIAL";
                e.printStackTrace();
                return;
            } finally {
                ultima = null;
                if (porta != null && porta.isOpen()) porta.closePort();
            }
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void lerEstados(SerialPort porta) throws IOException {
        long ultimaLinha = System.nanoTime();
        try (var entrada = new BufferedReader(new InputStreamReader(
            porta.getInputStream(), StandardCharsets.US_ASCII))) {
            while (ativo) {
                String linha;
                try {
                    linha = entrada.readLine();
                } catch (SerialPortTimeoutException e) {
                    // Abrir a porta pode reiniciar o Uno. Preserve a porta durante a inicialização.
                    if (System.nanoTime() - ultimaLinha >= LIMITE_SEM_LINHAS_NS) return;
                    continue;
                }
                if (linha == null) return;
                ultimaLinha = System.nanoTime();
                var estado = ESTADO.matcher(linha.trim());
                if (estado.matches()) {
                    ultima = new Leitura(estado.group(1).equals("APERTADO"),
                        estado.group(2).equals("APERTADO"), ultimaLinha);
                    situacao = "PORTA ABERTA, AGUARDANDO DADOS";
                } else if (!linha.isBlank()) {
                    situacao = "FORMATO SERIAL INESPERADO";
                }
            }
        }
    }

    @Override public void poll() {
        Leitura atual = ultima;
        quadro = atual != null && System.nanoTime() - atual.instante() < VALIDADE_NS ? atual : null;
    }
    @Override public boolean left() { return quadro != null && quadro.esquerda(); }
    @Override public boolean right() { return quadro != null && quadro.direita(); }
    @Override public boolean connected() { return quadro != null; }
    @Override public String label() {
        return connected() ? "ARDUINO CONECTADO" : nomePorta + "  |  " + situacao;
    }

    @Override public void close() {
        ativo = false;
        leitor.interrupt();
    }
}
