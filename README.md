# Colheita em Movimento

Nosso projeto será um jogo de coleta de frutas, feito em Java com libGDX e controlado por dois botões ligados ao Arduino.

A ideia é que a pessoa movimente um personagem para os lados e tente pegar as frutas que caem. Conforme ela avança, o jogo fica um pouco mais difícil.

## Objetivo

Queremos desenvolver um jogo voltado ao treino de coordenação do braço e da mão. O público que pensamos são pessoas com sequelas de AVC que consigam alcançar e apertar os botões.

O jogo será um projeto acadêmico de apoio à reabilitação. Não vamos avaliar recuperação clínica. Para usar com pacientes, seria necessário acompanhamento de um profissional da área.

## Como vai funcionar

- O botão da esquerda move o personagem para a esquerda.
- O botão da direita move o personagem para a direita.
- O jogador ganha pontos ao coletar frutas.
- O jogo terá três fases com dificuldade progressiva.
- Também teremos menu, pausa, reinício e uma tela de resultados.
- Os botões serão usados para controlar o jogo e navegar pelas telas.

## O que temos até agora

Por enquanto, organizamos a proposta, os requisitos e as tarefas. O código do jogo e do Arduino ainda precisa ser desenvolvido.

Também precisamos confirmar com o professor se podemos usar os botões no lugar das pedaladas e fazer a comunicação por USB. O enunciado permite outros dispositivos com autorização, mas a parte da avaliação menciona pedaladas.

## Organização dos arquivos

| Arquivo | O que tem nele |
| --- | --- |
| [Proposta](docs/01-proposta.md) | Ideia do jogo, objetivo e público. |
| [Requisitos](docs/02-requisitos.md) | O que precisamos entregar e como o jogo deve funcionar. |
| [Planejamento](docs/03-planejamento.md) | Divisão das tarefas até a entrega. |
| [Kanban](docs/04-kanban.md) | Lista de tarefas e andamento. |
| [Testes](docs/05-validacao.md) | O que vamos conferir durante o desenvolvimento. |
| [Arduino](docs/06-arduino-integracao.md) | Materiais e passos para ligar os botões ao jogo. |
| [Relatório](docs/07-estrutura-paper.md) | Organização do short paper. |
| [Contribuições](CONTRIBUTING.md) | Como vamos organizar os commits. |

## Integrantes

Ainda precisamos preencher os nomes. A divisão abaixo considera quatro pessoas e pode mudar conforme o grupo.

| Integrante | Parte principal |
| --- | --- |
| A preencher — A | Arduino e comunicação com o computador. |
| A preencher — B | Personagem, frutas e pontuação. |
| A preencher — C | Menu, telas e organização das fases. |
| A preencher — D | Documentação e registro dos testes, com ajuda de todos. |

Cada um terá sua parte, mas todos precisam entender como o projeto funciona.

## Prazo

A entrega está prevista para **04/11/2026**. Nossa ideia é terminar as funcionalidades até **30/10** e usar os últimos dias para testar e corrigir os problemas.

Vamos entregar o código-fonte, o executável e o short paper de até seis páginas. Também precisamos manter os commits individuais e apresentar o Kanban nas reuniões.

## Como executar

Ainda não temos uma versão executável. Vamos adicionar as instruções depois que o jogo estiver funcionando e tiver sido testado.

## Material usado como base

Estamos usando o enunciado `Projeto_Descricao_Geral.docx` e o exemplo de comunicação com Arduino do arquivo `laboratorio_arduino.zip`, fornecidos para o projeto.

O nome Colheita em Movimento é provisório.
