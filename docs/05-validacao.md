# Validação do jogo

## Registro de 10 de novembro de 2026

O grupo confirmou a leitura dos botões no Monitor Serial e o funcionamento do controle diretamente no jogo com Arduino físico. A confirmação final ocorreu na versão 0.2.2, depois do ajuste da biblioteca serial para o Windows.

| Área | Verificação | Resultado registrado |
| --- | --- | --- |
| Botões físicos | Pressão e soltura dos dois botões | Confirmadas no Monitor Serial |
| Integração direta | Conexão do Arduino e controle no jogo pela leitura serial em Java | Funcionamento confirmado pelo grupo na versão 0.2.2 |
| Regras | Pausa, reinício, bordas, fases, acionamentos, respostas e CSV | Dez testes automatizados aprovados |
| Comunicação simulada | Repouso, pressão, soltura, ausência de dados e retorno da comunicação | Verificada com porta serial simulada |
| Biblioteca serial | Seleção da DLL para AMD64, ARM64 e x86 | Arquitetura dos arquivos extraídos verificada |
| Partida completa no Arduino físico | Três fases, pausa, reinício, exportação e reconexão | Conferência final a registrar |
| Notebook da apresentação | Java, porta serial e execução do mesmo pacote | Conferência final a registrar |

Use o [roteiro da integração](06-arduino-integracao.md) para os testes finais. Registre a versão, o notebook, a porta e o resultado de cada teste. Os indicadores descrevem o desempenho no jogo.


## Revisão do pacote para a dupla

Na revisão do código, foram removidas as saídas de LED e corrigido o texto dos resultados para identificar o controle Arduino. O iniciador do Windows passou a procurar também o JAR gerado na pasta de build. A documentação de pontuação e o Kanban foram atualizados.

O build com Java 17 foi concluído e os dez testes JUnit passaram. O firmware foi compilado em um teste C++ com entradas simuladas para conferir os dois pinos, a filtragem e as mensagens. A leitura serial Java foi conferida com uma porta virtual, incluindo soltura, ausência de dados e retorno da comunicação. A parte 1 dos commits também compilou sobre a etapa 1.

A execução física desta revisão e o iniciador no Windows ainda precisam ser conferidos no notebook da apresentação. No teste físico, incluir toques breves, além de segurar e soltar os botões.
