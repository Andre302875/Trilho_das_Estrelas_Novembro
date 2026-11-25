# UML de novembro — módulo autónomo

O diagrama abaixo descreve as novas classes e o ponto de entrada único `trilho.Principal`, que abre a janela Swing por defeito e mantém a demonstração simples com `--outubro`.

```mermaid
classDiagram
    class Principal {
      +main(args: String[]) void
    }
    class JanelaNovembro {
      -jogo: JogoNovembro
      -tabuleiro: PainelTabuleiroNovembro
      +JanelaNovembro()
    }
    class JogoNovembro {
      -jogadores: List~JogadorNovembro~
      -turnoAtual: int
      -terminado: boolean
      -vencedor: JogadorNovembro
      +jogarTurno(dado: int) ResultadoNovembro
      +obterEfeito(casa: int) int
    }
    class JogadorNovembro {
      -nome: String
      -posicao: int
      +getNome() String
      +getPosicao() int
    }
    class ResultadoNovembro {
      -valorDado: int
      -posicaoAnterior: int
      -posicaoDado: int
      -posicaoFinal: int
      -efeito: int
      -venceu: boolean
    }
    class DadoNovembro {
      <<interface>>
      +lancar() int
    }
    class DadoManualNovembro {
      -valor: int
      +lancar() int
    }
    class DadoAleatorioNovembro {
      -gerador: Random
      +lancar() int
    }
    class PainelTabuleiroNovembro
    class ConfiguracaoNovembro
    class PainelArteNovembro
    Principal ..> JanelaNovembro
    JanelaNovembro *-- JogoNovembro
    JanelaNovembro *-- PainelTabuleiroNovembro
    JanelaNovembro *-- PainelArteNovembro
    JogoNovembro *-- "2..4" JogadorNovembro
    JogoNovembro ..> ResultadoNovembro
    DadoNovembro <|.. DadoManualNovembro
    DadoNovembro <|.. DadoAleatorioNovembro
    JanelaNovembro ..> DadoNovembro
```

**Separação:** modelo não tem `JOptionPane`, `println` ou componentes Swing. Interface concentra mensagens e recebe `ResultadoNovembro`; os dados implementam uma interface simples. `JogadorNovembro` valida nomes localmente, tal como o protótipo; as constantes partilhadas estão em `ConfiguracaoNovembro` para dispensar os ficheiros de outubro.
