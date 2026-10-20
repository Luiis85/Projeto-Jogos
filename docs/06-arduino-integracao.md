# Arduino e comunicação com o jogo

## Materiais previstos

| Material | Quantidade |
| --- | --- |
| Arduino Uno ou placa compatível com o laboratório | 1 |
| Cabo USB que transmita dados | 1 |
| Botões | 2 |
| Protoboard | 1 |
| Fios de conexão | Conforme a montagem |
| Base para fixar os botões | 1 |

Vamos conferir o que já temos e confirmar a proposta com o professor antes de comprar. Para a montagem final, a ideia é usar botões grandes, fáceis de apertar e bem fixados.

## Como os dados vão chegar ao jogo

A pessoa aperta o botão, o Arduino lê o estado e envia a informação pelo cabo USB. O programa em Java recebe esse dado e movimenta o personagem no libGDX.

O laboratório que recebemos já tem um exemplo de leitura pela porta serial e movimento para os lados. Vamos usar isso como base e identificar no projeto quais partes foram adaptadas.

Não encontramos um arquivo `.ino` no ZIP analisado. Ainda precisamos criar o programa que será enviado ao Arduino.

## Por onde vamos começar

1. Conseguir enviar um programa básico para a placa.
2. Ler um botão e conferir o resultado no monitor serial.
3. Fazer a leitura dos dois botões.
4. Definir como enviar os estados ao Java.
5. Conferir se o Java recebe os dados.
6. Usar os dados para mover o personagem.
7. Testar o que acontece ao soltar, apertar os dois juntos e desconectar o cabo.

## O que precisamos documentar depois da montagem

- Modelo da placa.
- Pinos usados e desenho ou foto das ligações.
- Formato das mensagens e velocidade da comunicação.
- Porta serial e como mudar essa configuração.
- Comandos usados no menu, na pausa e no reinício.

O leitor do laboratório guarda a última mensagem recebida. Precisamos conferir se ele não perde apertos rápidos e se o personagem para quando não deve mais receber o comando. Também precisamos evitar que a oscilação do contato de um botão seja contada como vários apertos.

Ainda não temos circuito nem comunicação testados pelo grupo neste pacote.
