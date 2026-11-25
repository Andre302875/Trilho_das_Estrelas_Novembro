# Regras implementadas em novembro

O tabuleiro tem casas **0 a 30**. Aceita **2–4 jogadores**; a demonstração gráfica inicia com 3. A ordem dos jogadores é fixa e as posições podem coincidir.

- **DadoNovembro aleatório:** gera de 1 a 6 inclusive, com `java.util.Random`.
- **DadoNovembro manual:** o utilizador escolhe um inteiro de 1 a 6.
- **Jogada:** avança o valor do dado. Só avança se não ultrapassar a casa 30.
- **Casas especiais:** se a posição após o dado for **5**, passa a **9**; se for **11**, volta a **7**; se for **22**, passa a **26**. Efeitos não se encadeiam; apenas um por turno.
- **Chegada exata:** ganha o primeiro a alcançar a casa **30**. Depois de terminar, não aceita novos lançamentos até nova partida.
- **Validação:** 2–4 nomes não vazios e diferentes, dado entre 1–6, rejeição de nova jogada após final. Inválidos não alteram o estado existente.
- **Casas restantes:** normais; 7, 9 e 26 são normais apesar de serem destinos de efeitos.

Exemplos: 0 + 5 = 5 ⇒ 9; 6 + 5 = 11 ⇒ 7; 18 + 4 = 22 ⇒ 26; 29 + 2 ⇒ fica em 29 e passa a vez; 29 + 1 ⇒ vitória.

Os efeitos especiais estão apenas no motor `JogoNovembro`; a demonstração de consola permanece sem efeitos, como no protótipo de outubro.
