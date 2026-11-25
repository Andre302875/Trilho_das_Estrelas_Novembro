/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

public enum ModoDadoNovembro {
    ALEATORIO("Aleatório"), MANUAL("Manual");
    private final String descricao;
    ModoDadoNovembro(String descricao) { this.descricao = descricao; }
    @Override public String toString() { return descricao; }
}
