# Colheita em Movimento

Rascunho do short paper — completar autores, referências consultadas, montagem e testes físicos após a etapa 2. Não é o artigo final para entrega.

## Problema e objetivo

O projeto propõe um jogo sério para tornar uma atividade repetitiva de coordenação de braço e mão mais envolvente. O público considerado no planejamento são pessoas com sequelas motoras de AVC que consigam alcançar e acionar os controles. Trata-se de uma proposta acadêmica; não foi realizada avaliação clínica e não se afirma eficácia terapêutica.

## Justificativa

A coleta de frutas oferece objetivos visíveis e retorno imediato. A progressão em três fases e o resumo dos resultados permitem acompanhar o desempenho dentro do jogo. A adequação do dispositivo e da atividade ao público pretendido ainda depende de avaliação profissional e da autorização do professor.

## Implementação digital

O jogo foi implementado em Java 17 e libGDX, com módulo core e inicializador desktop LWJGL3. As regras estão separadas da apresentação. A versão digital usa teclado para teste e oferece menu, instruções, pausa, reinício, personagem com cesta, frutas e obstáculos. Os elementos gráficos são desenhados em código.

As fases duram 45, 50 e 55 segundos. A primeira apresenta frutas grandes e lentas; a segunda reduz seu tamanho e aumenta a velocidade; a terceira introduz pedras. Frutas valem dez pontos e colisões com pedras retiram quinze. A pontuação de cada fase não fica negativa.

Comandos visuais solicitam um acionamento de esquerda ou direita em até três segundos. O tempo entre apresentação do comando e novo acionamento correto determina um bônus de dez a trinta pontos. A taxa de coleta, quantidade de acionamentos e média de resposta correta aparecem no acompanhamento ou arquivo CSV. Os dados desta versão são identificados como teclado de teste.

## Integração e avaliação pendentes

A etapa física será executada depois da versão digital. Ainda faltam circuito, programa da placa, comunicação e controle completo pelo dispositivo. Os testes automatizados verificam regras; o grupo deve registrar separadamente os testes de execução, integração e montagem efetivamente realizados. Inserir prints reais do jogo e foto do circuito antes da entrega.

## Limitações e conclusão provisória

A versão digital oferece uma base jogável para a integração. Seus indicadores descrevem desempenho na tarefa e não medem força, amplitude de movimento ou recuperação clínica. A conclusão final deve ser escrita após a integração física e a validação do conjunto.

## Referências a completar

Inserir o enunciado da disciplina, documentação oficial da libGDX e apenas referências de reabilitação efetivamente consultadas pelo grupo. Aplicar o modelo exigido pelo professor e verificar o limite de seis páginas na versão diagramada.
