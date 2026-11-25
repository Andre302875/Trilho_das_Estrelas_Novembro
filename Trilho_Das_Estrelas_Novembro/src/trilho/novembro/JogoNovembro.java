/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Regras jogáveis de novembro. Não usa Swing nem escreve na consola. */
public class JogoNovembro {
    private final List<JogadorNovembro> jogadores = new ArrayList<>();
    private int turnoAtual;
    private boolean terminado;
    private JogadorNovembro vencedor;

    public JogoNovembro(List<String> nomes) {
        if (nomes == null || nomes.size() < ConfiguracaoNovembro.MIN_JOGADORES || nomes.size() > ConfiguracaoNovembro.MAX_JOGADORES) {
            throw new IllegalArgumentException("Indique 2 a 4 jogadores.");
        }
        for (String nome : nomes) {
            JogadorNovembro novo = new JogadorNovembro(nome);
            for (JogadorNovembro existente : jogadores) {
                if (existente.getNome().equalsIgnoreCase(novo.getNome())) {
                    throw new IllegalArgumentException("Os nomes dos jogadores devem ser diferentes.");
                }
            }
            jogadores.add(novo);
        }
        turnoAtual = 0;
        terminado = false;
        vencedor = null;
    }

    public List<JogadorNovembro> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }
    public JogadorNovembro getJogadorAtual() { return jogadores.get(turnoAtual); }
    public boolean isTerminado() { return terminado; }
    public JogadorNovembro getVencedor() { return vencedor; }

    /**
     * Lança dado validado, move, aplica no máximo UM efeito e passa a vez.
     * O jogador só vence ao alcançar exatamente a casa 30.
     * Lançamentos inválidos não alteram jogadores nem turno.
     */
    public ResultadoNovembro jogarTurno(int dado) {
        if (terminado) {
            throw new IllegalStateException("A partida terminou. Inicie outra.");
        }
        if (dado < ConfiguracaoNovembro.DADO_MIN || dado > ConfiguracaoNovembro.DADO_MAX) {
            throw new IllegalArgumentException("O dado deve estar entre 1 e 6.");
        }

        JogadorNovembro atual = getJogadorAtual();
        int anterior = atual.getPosicao();
        int destinoDado = anterior + dado;
        boolean ultrapassou = destinoDado > ConfiguracaoNovembro.ULTIMA_CASA;
        int casaDado = ultrapassou ? anterior : destinoDado;
        int efeito = ultrapassou ? 0 : obterEfeito(casaDado);
        int finalTurno = casaDado + efeito;
        atual.moverPara(finalTurno);

        boolean venceu = finalTurno == ConfiguracaoNovembro.ULTIMA_CASA;
        if (venceu) {
            terminado = true;
            vencedor = atual;
        } else {
            turnoAtual = (turnoAtual + 1) % jogadores.size();
        }
        return new ResultadoNovembro(atual.getNome(), dado, anterior, casaDado,
                finalTurno, efeito, ultrapassou, venceu);
    }

    /** As únicas casas especiais definidas no mapa: 5, 11 e 22. */
    public static int obterEfeito(int casa) {
        switch (casa) {
            case 5: return 4;   // 5 -> 9
            case 11: return -4; // 11 -> 7
            case 22: return 4;  // 22 -> 26
            default: return 0;
        }
    }
}
