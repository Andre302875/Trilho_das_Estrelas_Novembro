/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

/** Dados de um turno, incluindo a casa em que o dado pousou e o efeito aplicado. */
public class ResultadoNovembro {
    private final String nomeJogador;
    private final int valorDado;
    private final int posicaoAnterior;
    private final int posicaoDado;
    private final int posicaoFinal;
    private final int efeito;
    private final boolean ultrapassou;
    private final boolean venceu;

    public ResultadoNovembro(String nomeJogador, int valorDado, int posicaoAnterior,
                             int posicaoDado, int posicaoFinal, int efeito,
                             boolean ultrapassou, boolean venceu) {
        this.nomeJogador = nomeJogador;
        this.valorDado = valorDado;
        this.posicaoAnterior = posicaoAnterior;
        this.posicaoDado = posicaoDado;
        this.posicaoFinal = posicaoFinal;
        this.efeito = efeito;
        this.ultrapassou = ultrapassou;
        this.venceu = venceu;
    }

    public String getNomeJogador() { return nomeJogador; }
    public int getValorDado() { return valorDado; }
    public int getPosicaoAnterior() { return posicaoAnterior; }
    public int getPosicaoDado() { return posicaoDado; }
    public int getPosicaoFinal() { return posicaoFinal; }
    public int getEfeito() { return efeito; }
    public boolean isUltrapassou() { return ultrapassou; }
    public boolean isVenceu() { return venceu; }
    public boolean isMoveu() { return posicaoAnterior != posicaoFinal; }
}
