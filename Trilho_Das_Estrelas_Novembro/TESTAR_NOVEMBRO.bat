@echo off
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -d out src\trilho\Principal.java src\trilho\novembro\*.java
if errorlevel 1 (
  echo ERRO: o codigo nao compilou.
  pause
  exit /b 1
)
java -cp out trilho.novembro.VerificacoesNovembro
pause
