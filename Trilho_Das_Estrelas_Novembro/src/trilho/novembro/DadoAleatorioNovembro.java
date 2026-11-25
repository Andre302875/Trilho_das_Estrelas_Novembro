/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

import java.util.Random;

/** DadoNovembro uniforme de 1 a 6. Permite injetar Random para testes repetíveis. */
public class DadoAleatorioNovembro implements DadoNovembro {
    private final Random gerador;

    public DadoAleatorioNovembro() { this(new Random()); }

    public DadoAleatorioNovembro(Random gerador) {
        if (gerador == null) {
            throw new IllegalArgumentException("Gerador aleatorio obrigatorio.");
        }
        this.gerador = gerador;
    }

    @Override
    public int lancar() { return gerador.nextInt(ConfiguracaoNovembro.DADO_MAX) + ConfiguracaoNovembro.DADO_MIN; }
}
