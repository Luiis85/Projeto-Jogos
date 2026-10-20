# Colheita em Movimento

Protótipo acadêmico de jogo sério em Java com libGDX e Arduino. A proposta é controlar um personagem por dois botões físicos e coletar frutas que caem na tela.

O público proposto são pessoas com sequelas motoras de AVC que consigam alcançar e pressionar os botões. O projeto explora uma atividade de coordenação de braço e mão; seu benefício clínico não foi avaliado.

## Situação atual

Este repositório começa pela documentação e pelo planejamento. Ainda não contém o jogo executável, firmware do Arduino ou testes realizados pelo grupo. O nome do jogo é provisório.

O uso dos botões como alternativa às pedaladas precisa ser autorizado pelo professor. O enunciado admite outros dispositivos com autorização prévia, mas sua seção de avaliação menciona pedaladas. A conexão prevista é USB/serial, aproveitando a abordagem do laboratório; confirmar também a aceitação dessa solução como integração com o dispositivo exigido.

## Funcionamento proposto

- Botão esquerdo: desloca o personagem para a esquerda.
- Botão direito: desloca o personagem para a direita.
- Frutas coletadas aumentam a pontuação.
- As três fases têm dificuldade progressiva.
- O dispositivo também permite iniciar, pausar e reiniciar a partida.
- Ao final, a tela apresenta pontuação e métricas da sessão.

As regras exatas de navegação com os dois botões serão definidas e testadas antes da versão final. Teclado, se usado para depuração, não será o controle da demonstração final.

## Documentação

| Arquivo | Conteúdo |
| --- | --- |
| [Proposta](docs/01-proposta.md) | Problema, público, objetivo e escopo. |
| [Requisitos](docs/02-requisitos.md) | Obrigações do enunciado e decisões propostas. |
| [Planejamento](docs/03-planejamento.md) | Divisão de trabalho e entregas até 04/11/2026. |
| [Kanban](docs/04-kanban.md) | Tarefas, dependências e critérios de conclusão. |
| [Validação](docs/05-validacao.md) | Roteiro de testes e métricas propostas. |
| [Arduino e integração](docs/06-arduino-integracao.md) | Componentes e próximos passos técnicos. |
| [Short paper](docs/07-estrutura-paper.md) | Estrutura para o documento de até seis páginas. |
| [Contribuição](CONTRIBUTING.md) | Como dividir trabalho e registrar commits. |

## Equipe

Divisão provisória para quatro integrantes. Substituir as identificações pelos nomes reais e ajustar a quantidade de pessoas antes de distribuir as tarefas.

| Identificação | Responsabilidade principal | Nome |
| --- | --- | --- |
| A | Arduino e comunicação serial | A preencher |
| B | Mecânicas e personagem | A preencher |
| C | Telas, controles de navegação e fases | A preencher |
| D | Documentação, organização e registro de validação | A preencher |

Todos devem compreender a integração e contribuir com trabalho próprio. A revisão e os testes são compartilhados.

## Entrega

Prazo informado pelo grupo: **04/11/2026**. Meta interna: funcionalidades prontas até **30/10/2026**.

Entregáveis previstos: código-fonte completo, executável desktop, short paper com até seis páginas e histórico de contribuições individuais. Manter também um quadro Kanban atualizado para o acompanhamento semanal.

## Como executar

Ainda não há aplicação neste pacote. As instruções reais de compilação, porta serial, montagem e execução serão adicionadas após a integração do laboratório e os testes no computador da equipe.

## Material de origem

- Enunciado fornecido: `Projeto_Descricao_Geral.docx`.
- Laboratório fornecido: `laboratorio_arduino.zip`, com exemplo Java/libGDX de leitura serial e movimento lateral.

Ao aproveitar o código do laboratório, identificar sua origem e descrever as alterações feitas pelo grupo. Este pacote não copia o código do professor nem seus arquivos compilados.
