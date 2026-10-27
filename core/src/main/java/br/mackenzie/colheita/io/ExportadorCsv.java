package br.mackenzie.colheita.io;

import br.mackenzie.colheita.model.ModeloJogo;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class ExportadorCsv {
    private ExportadorCsv() { }
    public static String content(ModeloJogo model) {
        StringBuilder out = new StringBuilder("modo;fase;duracao_s;coletadas;perdidas;colisoes;acionamentos;comandos;respostas_corretas;respostas_erradas;sem_resposta;taxa_coleta_pct;resposta_media_s;pontos_frutas;pontos_respostas;penalidade;pontos_fase\n");
        for (int i = 0; i < model.stats().size(); i++) {
            ModeloJogo.Stats s = model.stats().get(i);
            out.append(String.format(Locale.ROOT,
                "teclado_teste;%d;%.2f;%d;%d;%d;%d;%d;%d;%d;%d;%s;%s;%d;%d;%d;%d%n",
                i + 1, s.elapsed, s.collected, s.missed, s.collisions, s.presses, s.cues,
                s.correct, s.wrong, s.timedOut, number(s.accuracy()), number(s.meanResponse()),
                s.fruitPoints, s.responsePoints, s.penalty, s.score()));
        }
        return out.toString();
    }
    private static String number(double value) { return Double.isNaN(value) ? "" : String.format(Locale.ROOT, "%.3f", value); }
    public static Path save(ModeloJogo model, Path folder) throws IOException {
        if (model.state() != ModeloJogo.State.FINISHED) throw new IllegalStateException("Termine as tres fases antes de exportar.");
        Files.createDirectories(folder);
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS"));
        Path file = Files.createTempFile(folder, "sessao-" + stamp + "-", ".csv");
        Files.writeString(file, content(model), StandardCharsets.UTF_8);
        return file.toAbsolutePath();
    }
}
