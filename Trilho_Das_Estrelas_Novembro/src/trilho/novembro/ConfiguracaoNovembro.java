/* Constantes da versão de novembro, anteriormente reutilizadas do protótipo de outubro.
 * Esta classe permite que a entrega de novembro execute de forma autónoma.
 * Assistência de IA documentada em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

/** Limites comuns às regras, aos dados e ao tabuleiro. */
public final class ConfiguracaoNovembro {
    public static final int ULTIMA_CASA = 30;
    public static final int DADO_MIN = 1;
    public static final int DADO_MAX = 6;
    public static final int MIN_JOGADORES = 2;
    public static final int MAX_JOGADORES = 4;

    private ConfiguracaoNovembro() { }
}
