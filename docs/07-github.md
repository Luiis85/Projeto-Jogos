# Colocar no GitHub

1. Baixe e extraia o ZIP desta entrega.
2. Instale Git e JDK 17.
3. Abra um terminal numa pasta de trabalho e execute:
```
git clone https://github.com/Luiis85/Projeto-Jogos.git
cd Projeto-Jogos
git switch -c etapa-1-jogo
```
4. Copie o conteúdo da pasta `Projeto-Jogos` do ZIP para dentro do clone. Mescle as pastas e substitua arquivos quando solicitado. Copie também `.gitignore`; não copie uma pasta `.git` de outro local.
5. No PowerShell, confira:
```
.\gradlew.bat core:test lwjgl3:fatJar
.\gradlew.bat lwjgl3:run
git status
git add .
git commit -m "Implementa etapa digital do Colheita em Movimento"
git push -u origin etapa-1-jogo
```
6. No GitHub abra o repositório e clique em Compare & pull request. Revise e faça o merge para main.

Não envie `.gradle`, pastas `build`, resultados pessoais ou o ZIP do projeto para o código-fonte. O `.gitignore` já exclui esses itens. Para disponibilizar o executável, crie uma Release no GitHub e anexe o JAR gerado. Informe que requer Java 17 e ainda usa teclado.

O push usa sua conta e permissões habituais. Não coloque senha nem token dentro de arquivos. O ZIP não contém histórico Git; os comandos acima preservam o histórico do repositório existente. Cada integrante deve fazer commits reais do próprio trabalho; não recrie datas ou contribuições passadas.
