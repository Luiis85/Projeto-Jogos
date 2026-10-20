# Kanban inicial

Este arquivo contém o backlog inicial e um quadro em Markdown. Para facilitar a apresentação semanal, criar também um quadro no GitHub Projects, com colunas **A fazer**, **Em andamento**, **Em revisão** e **Concluído**, usando as tarefas abaixo como cartões.

Não há evidência de implementação neste pacote. Documentação gerada permanece em revisão até ser conferida pelo grupo. Os responsáveis A, B, C e D são provisórios.

## Quadro

| A fazer | Em andamento | Em revisão | Concluído |
| --- | --- | --- | --- |
| K02 a K15 | Nenhuma tarefa informada | K01 — documentação inicial | Nenhuma tarefa validada pelo grupo |

## Cartões

| ID | Tarefa | Responsável | Depende de | Critério de conclusão |
| --- | --- | --- | --- | --- |
| K01 | Revisar a documentação e preencher a equipe | Todos | — | Proposta, requisitos e nomes conferidos. |
| K02 | Confirmar botões e conexão USB com professor | Grupo | — | Decisão registrada, sem presumir autorização. |
| K03 | Confirmar componentes e ler dois botões | A | K02 para consolidar o dispositivo | Leitura real identificando pressionado e solto. |
| K04 | Executar e organizar o laboratório | B | — | Projeto executa e origem do exemplo está identificada. |
| K05 | Integrar Arduino e movimentação | A e B | K03, K04 | Botões movem e param personagem sem teclado. |
| K06 | Implementar coleta e perdas | B | K04 | Objetos e pontuação contados uma única vez. |
| K07 | Criar menu, pausa e reinício | C | K04, K05 para validar controle físico | Todas as ações são acessíveis pelo dispositivo. |
| K08 | Implementar três fases | B e C | K06 | Fases distintas com progressão demonstrável. |
| K09 | Definir e integrar métricas à pontuação | B e D | K05, K06 | Indicador derivado do dispositivo gera pontuação e é explicado. |
| K10 | Tratar falhas de comunicação | A e B | K05 | Ausência ou desconexão não mantém movimento indevido. |
| K11 | Exibir resultados | C | K09 | Resultados conferem com uma sessão controlada. |
| K12 | Redigir short paper | D, revisão de todos | K01, prints após K05 | Até seis páginas com conteúdo e prints reais. |
| K13 | Validar requisitos | Todos, registro por D | K07 a K11 | Resultados reais registrados e falhas corrigidas. |
| K14 | Gerar pacote executável | B | K13 | Jogo abre no ambiente alvo com instruções verificadas. |
| K15 | Conferir e entregar | Todos | K12, K14 | Código, executável e paper conferidos e enviados. |

## Regra para mover cartões

- Em andamento: alguém iniciou o trabalho e informou o grupo.
- Em revisão: há arquivo ou código disponível para conferir.
- Concluído: critério atendido e revisão registrada, com link para commit, alteração ou evidência.

## Registro de acompanhamento semanal

Preencher em cada reunião: data, tarefas concluídas, links das contribuições de cada integrante, bloqueios e entregas previstas para a próxima semana. Não registrar tarefas planejadas como realizadas.
