# Publicar os commits do grupo

O grupo é formado por Luis Otavio, Matheus Henrique e Matheus Romano. Cada pessoa usa a própria conta para publicar o trabalho que revisou e testou.

## Sua parte

O pacote Publicar_na_Main.zip publica na main o jogo existente e o commit do controle Arduino. O histórico é preservado. Depois, as branches antigas, já incorporadas à main, são removidas.

## Parte do colega

Depois da publicação da primeira parte, o responsável pela segunda parte deve seguir estes passos no site do GitHub.

1. Entrar com a própria conta e abrir o repositório Luiis85/Projeto-Jogos.
2. Selecionar a branch main.
3. Baixar e extrair GitHub_Parte_2_Jogo_Documentacao.zip.
4. Clicar em Add file e depois Upload files.
5. Arrastar todo o conteúdo extraído, incluindo as pastas inteiras core, docs e lwjgl3, além dos arquivos da raiz. Conferir os caminhos antes de publicar.
6. Usar a mensagem "Integra os botoes ao jogo e atualiza os documentos" e confirmar o commit na main.
7. Excluir docs/09-paper-rascunho.md, substituído por docs/09-paper.md, e registrar essa atualização.

Quem preferir publicar pelo Git pode clonar o repositório, copiar a parte 2 para o clone e executar:

```powershell
git switch main
git pull --ff-only
git rm docs/09-paper-rascunho.md
.\gradlew.bat core:test lwjgl3:fatJar
git diff --check
git add README.md INICIAR-JOGO.bat build.gradle core lwjgl3/src/main/java/br/mackenzie/colheita/desktop/InicializadorDesktop.java docs
git commit -m "Integra os botoes ao jogo e atualiza os documentos"
git push origin main
```

## Conferência final

Acompanhar a execução de Java build na aba Actions depois do commit. Com os testes aprovados, conferir o jogo no notebook da apresentação e registrar os resultados em docs/05-validacao.md.

O JAR pode ser anexado a uma Release. Os arquivos ZIP são usados para preparar os commits.
