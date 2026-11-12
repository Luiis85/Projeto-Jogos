# Verificação da etapa digital

Este documento registra os testes históricos da etapa 1. A situação da versão com Arduino está em [Validação do jogo](05-validacao.md).

Em 27/10/2026, o projeto foi compilado com JDK 17 e Gradle 8.14.1. Os cinco testes automatizados passaram: pausa/reinício, tecla segurada e limites, direções simultâneas, conclusão das três fases e bônus de resposta correta. O empacotamento fatJar foi concluído.

Não foi realizada inspeção visual da janela nem teste no Windows neste ambiente, que não dispõe de tela gráfica configurada. Antes de apresentar, execute o roteiro manual de 08-etapas.md no computador da entrega. Não houve teste com Arduino nem pacientes. O rascunho do artigo ainda requer autores, referências, imagens e resultados da integração.

## Correção dos comandos interrompidos

Um comando ativo ao fim da fase agora é registrado em `interrompidos_fim_fase`, sem erro, tempo esgotado ou penalidade. A coluna foi acrescentada ao final do CSV. Os arquivos exportados anteriormente não foram alterados.

Todas as classes principais foram compiladas com Java 17. Foram executadas nove verificações independentes das regras e da exportação: pausa e reinício, acionamento contínuo e bordas, direções simultâneas, três fases com reconciliação dos comandos, bônus de resposta, interrupção sem duplicação ou penalidade, resposta correta antes do fim, prazo esgotado e gravação do CSV. Todas passaram. Quatro testes de regressão foram acrescentados ao arquivo JUnit. Os nove testes JUnit passaram no GitHub Actions, na execução 40586785252 do pull request 1, commit 77cde3a. A janela gráfica corrigida não foi inspecionada neste ambiente.
