# Resultados técnicos de novembro — 24/11/2026

Compilação com OpenJDK 21 e opção `javac --release 11`: **concluída** (com avisos não impeditivos de análise estática sobre componentes Swing).

- Verificações do código original (`trilho.Verificacoes`, no projeto anterior): **24/24 OK** (não incluído neste ZIP).
- Verificações adicionadas (`trilho.novembro.VerificacoesNovembro`): **40/40 OK**.
- Interface Swing: abriu num ambiente gráfico de teste; no modo manual, lançar **5** moveu o primeiro jogador **0→5→9** e passou a vez ao seguinte; o modo aleatório também atualizou a vez.
- Captura ilustrativa: `../imagens/CAPTURA_GUI_NOVEMBRO.png` (tirada após as duas jogadas).

**Limitações:** não houve teste da GUI no computador dos estudantes nem execução completa de uma partida com cliques até à vitória. Estas validações ficam para o grupo realizar e registar nos commits.
