/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;


/** Estado mutável de uma peça de novembro, incluindo deslocações para trás. */
public class JogadorNovembro {
    private final String nome;
    private int posicao;

    public JogadorNovembro(String nome) {
        // Mantém a mesma regra de validação e limpeza de nomes do protótipo.
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome nao pode estar vazio.");
        }
        this.nome = nome.trim();
        this.posicao = 0;
    }

    public String getNome() { return nome; }
    public int getPosicao() { return posicao; }

    /** Apenas o motor deste package pode alterar a posição. */
    void moverPara(int novaPosicao) {
        if (novaPosicao < 0 || novaPosicao > ConfiguracaoNovembro.ULTIMA_CASA) {
            throw new IllegalArgumentException("Posicao fora do tabuleiro.");
        }
        posicao = novaPosicao;
    }
}
