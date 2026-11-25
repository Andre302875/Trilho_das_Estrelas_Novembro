# Trilho das Estrelas — versão jogável de novembro

Este ZIP contém apenas **`src/trilho/Principal.java`** (o ponto de entrada unificado) e os componentes **da versão de novembro**, com `Novembro` no nome. Não são necessários ficheiros Java de outubro.

## Abrir no IntelliJ IDEA
1. Extraia o ZIP e abra a pasta `Trilho_Das_Estrelas_Novembro` no IntelliJ IDEA.
2. Em **File → Project Structure**, selecione JDK 11 ou superior (preferência: 17 ou 21).
3. Se preciso, clique com o botão direito em `src` → **Mark Directory as → Sources Root**.
4. Abra `src/trilho/Principal.java` e clique em ▶ junto a `main()` → **Run 'Principal.main()'**.
5. Começa com **André, Tomás e Isaias**. A janela permite escolher de **2 a 4** jogadores numa nova partida.
6. Selecione o modo **Aleatório** ou **Manual** e clique em **Jogar dado**.

**Não é necessário instalar Gradle, Maven ou bibliotecas de terceiros.**

## Compilar e executar (terminal)
Na pasta raiz deste projeto:

```sh
javac -encoding UTF-8 -d out src/trilho/Principal.java src/trilho/novembro/*.java
java -cp out trilho.Principal
```

No Windows, pode utilizar `EXECUTAR_NOVEMBRO.bat` ou `TESTAR_NOVEMBRO.bat`.

## Progresso atual e reflexão

O progresso do checkpoint de novembro, as tarefas ainda incompletas, as instruções de execução, a reflexão, os próximos passos e uma dúvida a esclarecer com o professor estão descritos em [`docs/PROGRESSO_REFLEXAO_NOVEMBRO.md`](docs/PROGRESSO_REFLEXAO_NOVEMBRO.md).

## Testes

```sh
java -cp out trilho.novembro.VerificacoesNovembro
```

Os testes cobrem nomes, limites de jogadores, dado manual/aleatório, turnos, avanço, recuo, casas especiais, chegada exata e vitória.

## Regras
- Tabuleiro: casas **0 a 30**; começa na casa 0 e ganha quem chegar **exatamente à 30**.
- Dados: manual (1–6) ou aleatório (1–6).
- Casas especiais: **5 → 9 (+4)**, **11 → 7 (−4)** e **22 → 26 (+4)**; aplicam-se somente uma vez por turno.
- Se ultrapassar 30, o peão não avança e a vez passa.
- Permite 2–4 jogadores com nomes não vazios e diferentes.
- Botões para nova partida e para sair.

## Ficheiros do projeto

```text
Trilho_Das_Estrelas_Novembro/
├── src/trilho/Principal.java              # une Principal e PrincipalNovembro
├── src/trilho/novembro/
│   ├── ConfiguracaoNovembro.java          # constantes independentes de outubro
│   ├── JogoNovembro.java                  # lógica da partida
│   ├── JogadorNovembro.java               # jogador e posição
│   ├── ResultadoNovembro.java             # resultado de cada turno
│   ├── DadoNovembro.java                  # interface do dado
│   ├── DadoManualNovembro.java            # dado introduzido pelo jogador
│   ├── DadoAleatorioNovembro.java         # dado aleatório
│   ├── ModoDadoNovembro.java              # seleção do modo
│   ├── JanelaNovembro.java                # janela Swing
│   ├── PainelTabuleiroNovembro.java       # tabuleiro Swing
│   ├── PainelArteNovembro.java            # cabeçalho artístico
│   └── VerificacoesNovembro.java          # testes repetíveis
├── imagens/                               # arte e captura de novembro
├── docs/                                  # regras, UML, testes e conceito de novembro
├── README_NOVEMBRO.md
├── AI_USE_NOVEMBRO.md
├── EXECUTAR_NOVEMBRO.bat
└── TESTAR_NOVEMBRO.bat
```

## Execução alternativa de outubro
Para ver a demonstração de consola com os **mesmos valores predeterminados e sem efeitos especiais**, execute:

```sh
java -cp out trilho.Principal --outubro
```

Esta demonstração foi reexpressa dentro do único `Principal.java`, porque o pedido foi retirar do ZIP as classes de outubro. O jogo Swing utiliza as classes de novembro.

## Notas
- A imagem `imagens/CONCEITO_ARTISTICO_NOVEMBRO.png` aparece no cabeçalho quando a aplicação é executada a partir desta pasta; caso contrário há um fundo alternativo.
- O relatório de progresso é entregue **à parte**, não consta do ZIP.
- O grupo deve rever os ficheiros e completar os campos reais de `AI_USE_NOVEMBRO.md` e GitHub antes de entregar.
