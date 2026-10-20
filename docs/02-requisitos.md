# Requisitos do projeto

## O que o professor pediu

| Item | O que precisamos entregar |
| --- | --- |
| Tecnologia | Jogo desenvolvido com libGDX. |
| Controle | Ações do jogo controladas pelos dados do dispositivo. |
| Fases | Pelo menos três fases diferentes, com dificuldade progressiva. |
| Personagem | Pelo menos um personagem principal. |
| Menu | Opções para iniciar, pausar e reiniciar. |
| Avaliação | Um indicador obtido do dispositivo, usado na pontuação. |
| Código | Código-fonte completo no GitHub e commits semanais de cada integrante. |
| Acompanhamento | Kanban atualizado e explicação do que foi feito e do que falta fazer. |
| Aplicação | Executável ou APK funcionando. Vamos trabalhar com a versão para computador. |
| Short paper | Documento de até seis páginas com problema, justificativa, descrição do jogo, integração e prints. |

## Como pretendemos atender

- Usar dois botões conectados ao Arduino.
- Fazer o personagem andar para a esquerda ou para a direita.
- Coletar frutas e contar acertos, perdas e colisões com objetos que devem ser evitados.
- Aumentar a dificuldade entre as três fases.
- Usar os botões também nas opções do menu.
- Mostrar os resultados ao terminar.

Ainda precisamos da autorização do professor para usar os botões no lugar das pedaladas e a comunicação por USB.

## Pontuação e indicadores

Pretendemos contar frutas coletadas, frutas perdidas e acionamentos dos botões. Cada fruta deve ser contada uma única vez.

A taxa de coleta será:

`frutas coletadas / (frutas coletadas + frutas perdidas) × 100`

Se ainda não houver frutas avaliadas, vamos mostrar que não há dados, sem fazer a divisão.

Para contar acionamentos, precisamos identificar quando o botão passa de solto para pressionado. Manter o botão apertado não pode contar várias vezes como se fossem novos toques.

Contar apenas frutas não atende, sozinho, à exigência de usar um indicador do dispositivo na pontuação. Antes de implementar, vamos definir como relacionar os comandos físicos aos pontos e confirmar essa regra com o professor. Uma possibilidade é mostrar um comando na tela e dar pontos quando o botão indicado for apertado dentro do tempo definido.

Se medirmos tempo de resposta, ele será contado entre o aparecimento de um comando e o acionamento correto. Não vamos chamar qualquer intervalo entre apertos de tempo de resposta.

## Cuidados no funcionamento

- O personagem não pode sair da tela.
- O movimento deve parar ao soltar o botão.
- A pausa deve parar o tempo da partida e os objetos.
- Reiniciar deve limpar os dados da partida anterior.
- A falta de conexão com o Arduino precisa ser informada.
- Os resultados mostram desempenho no jogo, sem afirmar recuperação clínica.
