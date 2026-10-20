# Como contribuir

## Contribuições e autoria

O enunciado exige commits semanais de cada integrante. O grupo propõe organizar contribuições diárias para manter o trabalho distribuído. Um commit pode adicionar código, revisar documentação, corrigir falhas ou registrar uma validação real.

Cada pessoa usa sua própria conta e registra seu próprio trabalho. Não criar commits vazios, retroativos ou em nome dos colegas. Os arquivos iniciais são rascunhos preparados com apoio de IA e precisam de revisão e compreensão do grupo. Seguir as regras da disciplina sobre uso e declaração de IA.

## Fluxo simples

1. Conferir a tarefa e combinar quem editará cada arquivo.
2. Atualizar sua cópia antes de começar.
3. Fazer uma alteração pequena e coerente.
4. Verificar o comportamento ou revisar o texto modificado.
5. Criar commit explicando a alteração.
6. Enviar a contribuição e atualizar o Kanban.

Se usarem branches e pull requests, pedir a outro integrante que revise antes da integração. Evitar duas pessoas alterando a mesma parte de um arquivo ao mesmo tempo.

## Exemplos de mensagens

- `docs: adiciona proposta e requisitos iniciais`
- `docs: registra integrantes e divisão de tarefas`
- `feat: recebe estados dos botões pela serial`
- `feat: adiciona coleta de frutas`
- `fix: interrompe movimento ao perder conexão`
- `docs: registra resultado do teste de reinício`

Mensagens devem descrever trabalho realizado. Não usar “implementa Arduino” em um commit que contém apenas planejamento.

## Primeira distribuição possível

Uma pessoa pode adicionar este pacote inteiro como documentação inicial. Os demais devem fazer contribuições próprias após revisar: preencher os dados reais, ajustar requisitos acordados, registrar componentes disponíveis ou atualizar tarefas. Não reenviar o mesmo conteúdo como se fosse trabalho novo.

## Antes de concluir uma tarefa

- O critério do cartão foi atendido.
- Não foram enviados senhas, dados pessoais de pacientes ou arquivos temporários.
- A documentação não declara como funcionando algo que não foi testado.
- O grupo consegue explicar a alteração.

## Arquivos de código do laboratório

Quando o laboratório for incorporado, identificar sua origem e as alterações feitas pelo grupo. Manter o Gradle Wrapper, os scripts e as configurações necessários para reproduzir a compilação. A pasta `build` e o cache `.gradle` não devem entrar no histórico.
