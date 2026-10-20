# Plano de validação e registro de resultados

Status inicial: **todos os testes abaixo estão não executados**. Este documento é um roteiro; não comprova funcionamento.

| ID | Verificação | Resultado esperado | Estado |
| --- | --- | --- | --- |
| V01 | Conectar o dispositivo e ler os dois botões | Estado de cada botão identificado corretamente | Não executado |
| V02 | Pressionar e soltar cada botão | Personagem move na direção correta e para ao soltar | Não executado |
| V03 | Manter um botão pressionado | Movimento contínuo sem contar leituras como novos acionamentos | Não executado |
| V04 | Pressionar ambos os botões | Comportamento definido, documentado e sem travamento | Não executado |
| V05 | Movimentar até as bordas | Personagem permanece nos limites da tela | Não executado |
| V06 | Coletar uma fruta e perder outra | Cada evento altera seu contador uma única vez | Não executado |
| V07 | Colidir com objeto a evitar | Consequência corresponde à regra publicada | Não executado |
| V08 | Iniciar, pausar, continuar e reiniciar pelos botões | Ações funcionam sem teclado ou mouse | Não executado |
| V09 | Pausar uma partida | Tempo ativo e objetos não avançam durante a pausa | Não executado |
| V10 | Completar as três fases | Diferenças e progressão aparecem corretamente | Não executado |
| V11 | Reiniciar após uma partida | Contadores, objetos e tempo retornam ao estado inicial | Não executado |
| V12 | Jogar sem Arduino ou desconectá-lo | Aviso claro e interrupção de comandos antigos | Não executado |
| V13 | Conferir métricas com uma sequência conhecida | Valores exibidos correspondem aos eventos observados | Não executado |
| V14 | Gerar resultado sem frutas avaliadas | Taxa indica ausência de dados, sem divisão por zero | Não executado |
| V15 | Testar o executável fora da IDE | Abre no ambiente alvo conforme instruções | Não executado |

## Registro a preencher para cada execução

- Data e pessoa responsável:
- Versão ou commit:
- Computador e sistema operacional:
- Placa, porta serial e configuração:
- Teste executado:
- Procedimento e entradas:
- Resultado observado:
- Estado: aprovado, reprovado ou bloqueado.
- Evidência ou problema registrado:

## Métricas da sessão

Registrar fase, configurações, tempo ativo sem pausas, frutas coletadas, frutas perdidas, erros e acionamentos válidos. Confirmar no documento de requisitos o indicador derivado do dispositivo que será usado na pontuação.

Medir tempo de resposta exige um estímulo com início conhecido. Uma resposta não deve ser inferida apenas porque houve um movimento durante a queda de uma fruta.

## Limites

Os testes iniciais verificam o software e a integração. Não usar dados fictícios como resultados reais nem apresentar testes entre colegas como validação clínica. O grupo não deve coletar dados identificáveis de pacientes neste protótipo.
