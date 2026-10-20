# Arduino e integração

Este documento organiza os próximos passos. Ainda não há firmware, circuito validado ou integração executada neste pacote.

## Componentes propostos

| Item | Quantidade prevista | Observação |
| --- | --- | --- |
| Arduino Uno compatível com o laboratório | 1 | Confirmar qual placa o grupo possui. |
| Cabo USB de dados | 1 | Deve transmitir dados, além de alimentar. |
| Botões de acionamento leve | 2 | Para a base final, preferir superfície grande e fixação estável. |
| Protoboard | 1 | Para montagem inicial sem solda. |
| Fios de conexão | Conforme a montagem | Verificar conectores dos botões. |
| Base para fixação | 1 | Evitar que os botões deslizem durante o uso. |

Não comprar antes de confirmar o dispositivo com o professor e conferir o material já disponível. Valores e fornecedores não foram pesquisados neste pacote.

## Fluxo de dados previsto

Botões físicos → Arduino → cabo USB e porta serial → leitor Java → comandos do jogo libGDX.

O laboratório recebido já apresenta leitura de linhas pela serial e movimento lateral. Ele é ponto de partida, não uma aplicação completa. Não foi localizado arquivo `.ino` no ZIP inspecionado; o programa da placa ainda precisa ser desenvolvido.

## Sequência de implementação

1. Identificar placa e cabo e conseguir enviar um programa básico à placa.
2. Ler um botão e verificar os estados no monitor serial.
3. Ler os dois botões e distinguir pressionado e solto.
4. Definir o tratamento de ambos pressionados.
5. Definir um protocolo simples de estados com separação entre mensagens e velocidade serial igual nas duas pontas.
6. Fazer o Java receber e registrar os valores.
7. Conectar os estados à movimentação e testar soltura, bordas e desconexão.
8. Tratar contato instável dos botões para não duplicar acionamentos.

## Decisões técnicas a registrar após testar

- Modelo da placa e configuração escolhida.
- Pinos e diagrama real do circuito.
- Uso de resistores internos ou externos conforme a montagem adotada.
- Formato exato das mensagens e velocidade serial.
- Porta usada e como configurá-la em outro computador.
- Frequência de transmissão e comportamento quando não há dados novos.
- Lógica para navegar no menu, pausar e reiniciar com apenas dois botões.

O leitor do laboratório armazena a última linha recebida. Se o protocolo transmitir apenas eventos muito curtos, eles podem ser substituídos antes que o jogo os processe. Avaliar envio periódico de estados para movimentação e tratamento confiável dos eventos usados em pontuação e menus.

## Autoria e arquivos

Ao incorporar o laboratório, preservar os avisos existentes, identificar o material base no README e registrar o que foi alterado. Não enviar pastas de compilação, caches ou classes compiladas junto do código-fonte.
