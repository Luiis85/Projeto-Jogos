# Colheita em Movimento

Luis Otavio, Matheus Henrique e Matheus Romano

Jogo sério desenvolvido em Java 17 e libGDX para praticar acionamentos direcionados com um controle de dois botões. A versão desktop recebe os estados do Arduino Uno por USB e usa os botões para movimentar a cesta e navegar pelas telas.

O grupo confirmou o funcionamento da integração direta com o Arduino na versão 0.2.2. O registro está em [Validação do jogo](docs/05-validacao.md).

## Como executar no Windows

1. Confira a montagem em [Arduino e comunicação com o jogo](docs/06-arduino-integracao.md).
2. Abra `arduino/ControleColheita/ControleColheita.ino` no Arduino IDE, selecione Arduino Uno e a porta da placa e envie o programa. O sketch de teste anterior que já enviava os dois estados no mesmo formato também é compatível.
3. Em **Tools → Serial Monitor**, selecione 9600 baud. Em repouso, os dois botões devem mostrar `SOLTO`. Confira a passagem para `APERTADO` e de volta a `SOLTO` individualmente.
4. Feche o Monitor Serial e encerre qualquer ponte PowerShell antiga. Abra `INICIAR-JOGO.bat` na pasta do executável. Informe a porta exibida no Arduino IDE, por exemplo `COM3`.
5. Com a mensagem **ARDUINO CONECTADO** na tela, solte os botões. Aperte os dois juntos para iniciar.

| Tela | Branco | Azul | Ambos juntos |
| --- | --- | --- | --- |
| Menus e resultados | Opção anterior ao soltar | Próxima opção ao soltar | Confirmar |
| Partida | Mover para a esquerda | Mover para a direita | Pausar |

Na pausa, selecione Continuar, Reiniciar partida ou Voltar ao menu. Solte os dois depois de confirmar ou pausar. Se o cabo desconectar durante a partida, o jogo pausa. Após reconectar, solte os botões e confirme Continuar. A coluna `modo` do CSV registra `arduino` nas sessões dessa versão.

## Se o jogo não conectar

- **PORTA INDISPONIVEL**. Confira a porta no Arduino IDE e feche o Monitor Serial e a ponte PowerShell.
- **PORTA ABERTA, AGUARDANDO DADOS**. Aguarde a inicialização da placa. Se a mensagem persistir, confirme o sketch e o envio no Monitor Serial a 9600 baud.
- **FORMATO SERIAL INESPERADO**. Envie o sketch do projeto e confira se aparecem linhas como `Branco: SOLTO | Azul: SOLTO`.

Em outro notebook, confira o Java com `java -version` e identifique a porta do Arduino nesse computador. O firmware acompanha a placa.

## Código-fonte

Use um JDK 17 ou superior. Para executar pelo código ou construir o JAR no Windows:

```powershell
.\gradlew.bat lwjgl3:run -Pporta=COM3
.\gradlew.bat core:test lwjgl3:fatJar
```

O JAR é criado em `lwjgl3/build/libs/colheita-em-movimento-0.2.2.jar`. Depois de compilar, você também pode abrir `INICIAR-JOGO.bat` na raiz do projeto. Para abri-lo pelo terminal com a porta escolhida:

```powershell
java -jar colheita-em-movimento-0.2.2.jar --porta COM3
```

No Linux, use `bash gradlew` e substitua `COM3` pelo caminho da sua porta serial. O script de início do pacote é para Windows.

## Projeto e avaliação

- [Proposta](docs/01-proposta.md)
- [Kanban](docs/03-kanban.md)
- [Testes de aceitação](docs/05-validacao.md)
- [Integração e montagem](docs/06-arduino-integracao.md)
- [Texto do short paper](docs/09-paper.md)

A pontuação de cada fase considera frutas coletadas, penalidades por colisões e bônus pelo tempo de resposta a comandos visuais. O CSV registra esses indicadores por fase, na pasta `ColheitaEmMovimento/resultados` dentro da pasta pessoal do usuário.
