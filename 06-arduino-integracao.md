# Arduino e comunicação com o jogo

## Montagem

Desconecte o USB enquanto monta. Os botões usam o resistor de pull-up interno e fecham o circuito para o GND quando pressionados.

| Componente | Ligação |
| --- | --- |
| GND do Uno | Faixa azul (−) da protoboard |
| Branco | Um contato ao pino digital 2 e o outro ao GND |
| Azul | Um contato ao pino digital 3 e o outro ao GND |

Na montagem testada, o botão branco fica no bloco f–j e o azul em uma área livre do bloco a–e. Use dois contatos do botão que só se conectem ao apertar. Cada jumper deve compartilhar a fileira e o lado da canaleta com o terminal correspondente. A faixa usada para GND deve ser eletricamente comum aos dois botões.

## Comunicação serial

O sketch estabiliza a leitura por 25 ms e envia os estados a cada 50 ms pela porta serial a 9600 baud, 8 bits, sem paridade, um bit de parada. Exemplo de linha:

```text
Branco: SOLTO | Azul: APERTADO
```

O módulo desktop abre a porta com jSerialComm. A partida usa o estado mais recente, descarta leituras antigas e pausa se deixar de receber dados. Quando a conexão retorna, os botões devem ser soltos antes de retomar. O Monitor Serial deve estar fechado enquanto o jogo usa a porta.

## Conferência local

1. No Monitor Serial, teste os estados de repouso, pressão e soltura dos dois botões.
2. No jogo, inicie com ambos e confira as duas direções e a parada ao soltar.
3. Pause, continue e reinicie pelo controle. Conclua as três fases e exporte o CSV.
4. Desconecte o USB durante uma fase e confira a pausa. Reconecte, solte os botões e confirme Continuar.
