# Proposta do projeto

Status: proposta inicial para revisão do grupo e aprovação do professor.

## Problema e contexto

O projeto investiga como um jogo simples pode tornar uma atividade repetitiva de alcançar e pressionar alvos mais envolvente. O recorte proposto é o treino de coordenação de braço e mão em pessoas com sequelas motoras de AVC que apresentem movimento suficiente para operar o dispositivo.

A proposta não foi validada com pacientes. O protótipo não diagnostica condições, prescreve exercícios ou comprova recuperação clínica. Uma eventual aplicação com pacientes depende da avaliação de profissionais de reabilitação e das orientações da instituição.

## Objetivo geral

Desenvolver um jogo funcional em libGDX, controlado por dados de dois botões conectados ao Arduino, com três fases e registro de desempenho durante a sessão.

## Objetivos específicos

- Ler os estados dos botões e transmitir os dados ao computador.
- Associar esses dados à movimentação do personagem.
- Criar coleta de frutas, pontuação e progressão entre três fases.
- Permitir iniciar, pausar e reiniciar pelo dispositivo.
- Apresentar métricas derivadas da interação e do desempenho no jogo.
- Documentar a montagem, a execução e os limites da proposta.

## Atividade física proposta

Dois botões grandes são fixados em uma base sobre uma mesa. Uma possibilidade é operar os dois com a mesma mão, deslocando-a entre os alvos. A posição dos botões e o esforço de acionamento precisam ser adequados ao usuário com orientação profissional em uma aplicação clínica.

O circuito detecta acionamentos. Ele não mede a trajetória do braço, a amplitude articular ou a força muscular, nem confirma que o movimento corporal esperado foi executado.

## Jogo

Um personagem com uma cesta se movimenta horizontalmente para coletar frutas. O cenário pode permanecer o mesmo nas três fases, com indicação visual da etapa.

| Fase | Mecânica proposta | Progressão |
| --- | --- | --- |
| 1 | Coletar frutas grandes que caem lentamente | Aprender os comandos. |
| 2 | Coletar frutas menores | Exigir maior precisão de posicionamento. |
| 3 | Coletar frutas e evitar um tipo de objeto | Exigir seleção e precisão. |

A duração, os tamanhos e as velocidades serão definidos após o primeiro protótipo. A dificuldade deve ser ajustável; aumentar a velocidade não será a única forma de progressão.

## Escopo mínimo

Um personagem, movimento horizontal, um tipo de fruta, um tipo de objeto a evitar, três fases, menu, pausa, reinício e resultados. A versão alvo é desktop com conexão USB/serial.

Ficam fora desta primeira entrega: aplicativo para celular, nuvem, cadastro de pacientes, banco de dados, multiplayer, luva sensorizada, plataforma de equilíbrio e avaliação clínica.

## Decisões pendentes

- Aprovação do professor para botões em lugar das pedaladas e para conexão USB/serial.
- Confirmação dos integrantes e dos componentes disponíveis.
- Nome definitivo e regras de navegação do menu com apenas dois botões.
- Escolha final dos indicadores e das regras de pontuação.

## Referência para fundamentação

Consultar a diretriz NICE NG236, seção de treino repetitivo de tarefas: https://www.nice.org.uk/guidance/ng236/chapter/Recommendations

Essa referência discute reabilitação após AVC; não constitui evidência de eficácia deste protótipo. O grupo deve ler a seção utilizada e registrar a referência no formato solicitado pelo professor.
