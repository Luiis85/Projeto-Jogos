# Requisitos e critérios de aceitação

Os requisitos obrigatórios abaixo foram extraídos do enunciado fornecido. As decisões de implementação são propostas do grupo e podem ser alteradas antes da implementação.

## Obrigações do enunciado

| ID | Requisito | Evidência esperada |
| --- | --- | --- |
| O01 | Desenvolver o jogo com libGDX | Código-fonte e jogo executando. |
| O02 | Controlar o jogo exclusivamente por dados do dispositivo | Demonstração sem controle de jogo pelo teclado ou mouse. |
| O03 | Incluir pelo menos três fases distintas com dificuldade progressiva | Partida demonstrando as três fases e suas diferenças. |
| O04 | Incluir pelo menos um personagem principal | Personagem visível e funcional. |
| O05 | Permitir iniciar, pausar e reiniciar | Ações funcionando por meio do dispositivo. |
| O06 | Monitorar velocidade, consistência ou outro indicador derivado do dispositivo e utilizá-lo na pontuação | Fórmula documentada, coleta funcional e resultados exibidos. |
| O07 | Entregar código-fonte e executável ou APK | Código completo e artefato que execute no ambiente alvo. |
| O08 | Entregar short paper de até seis páginas | Problema, contexto, justificativa, descrição do jogo, integração e prints reais. |
| O09 | Registrar commits semanais de cada integrante | Histórico individual com contribuições identificáveis. |
| O10 | Apresentar Kanban e progresso nas reuniões semanais | Quadro atualizado, resumo da semana e próximos passos. |

## Propostas de implementação

| ID | Proposta | Critério de aceitação |
| --- | --- | --- |
| P01 | Dois botões no Arduino com USB/serial | Estados dos dois botões chegam ao programa e controlam o personagem. |
| P02 | Personagem com movimento lateral | Não sai dos limites da tela e para ao soltar o comando. |
| P03 | Coleta de frutas | Cada colisão válida conta uma única vez. |
| P04 | Frutas perdidas | Fruta que sai da tela é removida e registrada uma única vez. |
| P05 | Objeto a evitar na fase 3 | Colisão gera consequência documentada uma única vez. |
| P06 | Resultados ao final | Exibe pontuação e os indicadores implementados. |
| P07 | Tratamento de conexão ausente | Informa o problema e não aceita dados antigos como novos movimentos. |
| P08 | Reinício consistente | Zera contadores, tempo e objetos da partida anterior. |

## Regras de métricas propostas

- Acertos: frutas coletadas.
- Perdas: frutas não coletadas.
- Erros: colisões com o objeto a evitar.
- Taxa de coleta: coletadas / (coletadas + perdidas) × 100. Se não houver frutas avaliadas, mostrar “sem dados”.
- Acionamentos: transições válidas de solto para pressionado, com tratamento de contato instável do botão. Não contar cada leitura contínua como um novo acionamento.
- Consistência: opcionalmente, variação dos intervalos entre acionamentos em uma tarefa com ritmo explicitamente definido. Não interpretar pausas estratégicas como falhas motoras.

Proposta para incluir dados do dispositivo na pontuação: em cada fase, reservar uma pequena etapa de comandos indicados na tela. Um acerto exige pressionar o botão indicado dentro de uma janela de tempo configurável. Um mesmo acionamento não pode valer para mais de um alvo. Os acertos dessa etapa geram bônus. Essa regra depende da validação de viabilidade e deve ser aprovada pelo grupo antes de programar.

Tempo de resposta só deve ser calculado nessa etapa com estímulo explícito: instante do acionamento correto menos instante de apresentação do comando. Não usar a queda de uma fruta qualquer como medida automática de resposta.

## Limites da avaliação

Métricas de jogo descrevem desempenho na atividade. Não são escalas clínicas. Comparações entre sessões só fazem sentido com configurações registradas e equivalentes. O Arduino com botões não mede força ou amplitude.

## Aprovação necessária

P01 depende do professor: o enunciado permite outros dispositivos mediante autorização prévia, mas a rubrica final cita pedaladas. Registrar a decisão antes de consolidar o hardware.
