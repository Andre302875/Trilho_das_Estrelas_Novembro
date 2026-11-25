/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;


/** Utiliza o valor escolhido pelo utilizador; não gera valores. */
public class DadoManualNovembro implements DadoNovembro {
    private final int valor;

    public DadoManualNovembro(int valor) {
        if (valor < ConfiguracaoNovembro.DADO_MIN || valor > ConfiguracaoNovembro.DADO_MAX) {
            throw new IllegalArgumentException("Escolha um valor do dado entre 1 e 6.");
        }
        this.valor = valor;
    }

    @Override
    public int lancar() { return valor; }
}
