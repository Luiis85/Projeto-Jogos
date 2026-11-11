@echo off
setlocal
cd /d "%~dp0"
where java >nul 2>&1
if errorlevel 1 (
  echo Instale Java 17 ou superior e tente novamente.
  pause
  exit /b 1
)
set "JOGO_JAR=colheita-em-movimento-0.2.2.jar"
if not exist "%JOGO_JAR%" set "JOGO_JAR=lwjgl3\build\libs\colheita-em-movimento-0.2.2.jar"
if not exist "%JOGO_JAR%" set "JOGO_JAR=..\Executavel\colheita-em-movimento-0.2.2.jar"
if not exist "%JOGO_JAR%" (
  echo Compile o projeto com gradlew.bat core:test lwjgl3:fatJar
  echo Ou abra INICIAR-JOGO.bat na pasta Executavel do pacote.
  pause
  exit /b 1
)
set "PORTA="
set /p "PORTA=Porta do Arduino [COM3]: "
if not defined PORTA set "PORTA=COM3"
java --enable-native-access=ALL-UNNAMED -jar "%JOGO_JAR%" --porta "%PORTA%"
if errorlevel 1 pause
