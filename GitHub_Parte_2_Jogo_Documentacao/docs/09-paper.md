# Colheita em Movimento

Luis Otavio, Matheus Henrique e Matheus Romano

## 1 Problema e objetivo

Exercícios de alcance e acionamento podem envolver a repetição de movimentos direcionados da mão e do braço. O projeto propõe uma tarefa de jogo voltada ao contexto da reabilitação motora após AVC. A pessoa se senta à mesa e usa dois botões em posições distintas para conduzir um personagem com uma cesta. O objetivo acadêmico é transformar cada acionamento em uma ação observável e registrar o desempenho ao longo da partida.

## 2 Justificativa

O pomar dá um objetivo imediato à atividade. A cesta responde ao comando, as frutas coletadas aparecem na pontuação e os avisos de direção dão oportunidades de praticar a resposta a um estímulo visual. As três fases aumentam a exigência aos poucos. A sequência permite observar a coleta, a precisão dos comandos e o tempo de resposta em condições de dificuldade diferentes.

## 3 Implementação do jogo

O jogo foi desenvolvido em Java com libGDX e um módulo desktop LWJGL3. O personagem recolhe frutas e evita pedras. A primeira fase apresenta frutas maiores e lentas. A segunda reduz o tamanho e aumenta a velocidade. A terceira acrescenta pedras. As fases duram 45, 50 e 55 segundos. O jogo oferece instruções, pausa, reinício, resumo das fases e exportação CSV.

Cada fruta vale dez pontos e uma colisão com pedra desconta quinze. Quando aparece uma seta, o jogador tem até três segundos para soltar e acionar a direção pedida. Respostas corretas rendem de dez a trinta pontos conforme o tempo. A tela de resultados e o CSV permitem comparar a taxa de coleta, os acertos e o tempo médio de resposta por fase. A diferença de dificuldade entre fases deve ser considerada ao comparar os dados.

## 4 Integração com o Arduino

Os botões branco e azul se conectam aos pinos digitais 2 e 3 do Arduino Uno e ao GND. O firmware usa o pull-up interno e filtra as oscilações dos contatos. O Arduino envia o estado dos dois botões pela conexão USB a 9600 baud. A aplicação desktop lê os estados por meio da biblioteca jSerialComm.

Durante a partida, o branco move a cesta para a esquerda, o azul para a direita e a soltura interrompe o movimento. Apertar os dois pausa a fase. Nos menus, cada botão muda a opção ao ser solto e o acionamento conjunto confirma. Se a comunicação parar, a partida pausa e o jogo espera a reconexão. A coluna modo identifica as sessões do controle como arduino.

## 5 Verificação e limites

Na montagem física, o grupo confirmou a leitura dos dois botões no Monitor Serial e fez um primeiro teste de movimentação com uma ponte em PowerShell. Depois da implementação da leitura serial em Java e da correção do carregamento da biblioteca no Windows, o grupo confirmou o funcionamento do controle diretamente no jogo, na versão 0.2.2.

Os dez testes automatizados do módulo de regras passaram. Eles verificaram pausa, reinício, bordas, acionamentos, conclusão das fases, pontuação e identificação das sessões do Arduino no CSV. A comunicação também foi verificada com uma porta serial simulada, incluindo pressão, soltura, ausência de dados e retorno da conexão. A conferência final compreende uma partida completa no dispositivo, a exportação do CSV e a execução no notebook da apresentação.

Os indicadores descrevem o desempenho na tarefa. O dispositivo registra estados e tempos de acionamento. A proposta acadêmica não foi avaliada clinicamente.

## Referências

- Enunciado da disciplina. Projeto Final de Desenvolvimento de Jogo Sério para Reabilitação com IoT e libGDX.
- libGDX. Documentação do framework. https://libgdx.com/wiki/
- Fazecast. Documentação do jSerialComm. https://fazecast.github.io/jSerialComm/
