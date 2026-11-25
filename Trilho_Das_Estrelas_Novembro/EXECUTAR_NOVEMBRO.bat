@echo off
cd /d "%~dp0"
if not exist out mkdir out
javac -encoding UTF-8 -d out src\trilho\Principal.java src\trilho\novembro\*.java
if errorlevel 1 (
  echo ERRO: confirme se o JDK esta instalado e o javac esta no PATH.
  pause
  exit /b 1
)
java -cp out trilho.Principal
if errorlevel 1 pause
