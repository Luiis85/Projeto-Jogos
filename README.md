# Colheita em Movimento

Jogo Java 17 com libGDX. Etapa 1: versão digital com teclado de teste. Etapa 2: Arduino, circuito e controle físico ainda pendentes. Esta versão não atende ainda ao controle exclusivo pelo dispositivo exigido na entrega final.

## Executar

Instale o JDK 17 e confira `java -version`. Na pasta que contém `gradlew.bat`:

Windows PowerShell:
```
.\gradlew.bat lwjgl3:run
```
Linux/macOS:
```
chmod +x gradlew
./gradlew lwjgl3:run
```
A primeira execução precisa de internet para baixar Gradle e dependências.

## Controles

- A/D ou setas: movimentar; soltar para parar; ambas juntas deixam parado.
- Tab/setas e Enter: menus. Mouse também funciona.
- P/Esc: pausar e continuar. R na pausa: reiniciar toda a partida.
- Resultados: exportar CSV na pasta pessoal `ColheitaEmMovimento/resultados`.

## Regras

Três fases de 45, 50 e 55 segundos. Frutas ficam menores e mais rápidas. Na fase 3 surgem pedras. Fruta coletada: +10; pedra: -15. Pontuação mínima por fase: zero. Não há vidas nem eliminação.

Comandos visuais de esquerda/direita pedem um novo acionamento em até 3 segundos. Resposta correta vale `10 + arredondar(20 × (1 − tempo/3))` pontos. Erro ou falta de resposta não pontuam. Segurar antes da seta exige soltar e apertar novamente. Acionamentos são contados pela transição de solto para pressionado.

A taxa de coleta considera coletadas/(coletadas+perdidas). Objetos ainda no ar ao terminar a fase não contam como perdas. Tempo médio considera somente respostas corretas; ausência de dados aparece como -- e célula vazia no CSV. Resultados são desempenho no jogo, sem comprovação clínica.

Quando a fase termina com um comando ainda ativo, ele é registrado na coluna `interrompidos_fim_fase` do CSV, sem erro ou penalidade. Ao concluir cada fase, o total de comandos corresponde à soma de respostas corretas, erradas, sem resposta e interrompidas. A nova coluna fica no final, preservando a ordem das colunas anteriores. Arquivos CSV já exportados permanecem inalterados.

## Compilar e testar

```
./gradlew core:test lwjgl3:fatJar
```
No Windows use `.\gradlew.bat` no lugar de `./gradlew`.
O JAR fica em `lwjgl3/build/libs/colheita-em-movimento-0.1.0.jar`.
Execute com `java -jar caminho/do/arquivo.jar`. No macOS use `java -XstartOnFirstThread -jar caminho/do/arquivo.jar`. Requer Java 17 e ambiente gráfico com OpenGL. O JAR não inclui uma JVM.

## Organização

- `core/src/main/java`: regras, apresentação, teclado e exportação CSV.
- `core/src/test/java`: testes automatizados das regras.
- `lwjgl3`: inicialização desktop e empacotamento.
- `docs/07-github.md`: instruções para publicar.
- `docs/08-etapas.md`: entregas e limitações.
- `docs/09-paper-rascunho.md`: texto inicial do artigo, a completar após integração.

Gráficos originais desenhados em código. Wrapper Gradle aproveitado do material do laboratório. Não foi incorporado leitor serial nem programa da placa. A interface `ControlesJogo` delimita a futura fonte dos estados esquerda/direita; navegação física e tratamento da conexão também precisam ser implementados na etapa 2.
