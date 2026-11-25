/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/** Tabuleiro Swing de 31 casas, reutilizado depois de cada jogada. */
public class PainelTabuleiroNovembro extends JPanel {
    private static final Color FUNDO = new Color(12, 22, 48);
    private static final Color NORMAL = new Color(37, 60, 104);
    private static final Color AVANCO = new Color(18, 111, 96);
    private static final Color RECUO = new Color(146, 63, 88);
    private static final Color META = new Color(118, 90, 37);
    private static final Color[] CORES = {
            new Color(65, 209, 242), new Color(255, 126, 116),
            new Color(255, 215, 90), new Color(170, 146, 255)
    };
    private final Casa[] casas = new Casa[ConfiguracaoNovembro.ULTIMA_CASA + 1];
    private List<JogadorNovembro> jogadores = Collections.emptyList();

    public PainelTabuleiroNovembro() {
        super(new GridLayout(6, 6, 8, 8));
        setBackground(FUNDO);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        for (int i = 0; i <= ConfiguracaoNovembro.ULTIMA_CASA; i++) {
            casas[i] = new Casa(i);
            add(casas[i]);
        }
        for (int i = ConfiguracaoNovembro.ULTIMA_CASA + 1; i < 36; i++) {
            JPanel vazio = new JPanel();
            vazio.setOpaque(false);
            add(vazio);
        }
    }

    public void atualizar(JogoNovembro jogo) {
        this.jogadores = jogo.getJogadores();
        repaint();
    }

    public static Color corJogador(int indice) { return CORES[indice % CORES.length]; }

    private class Casa extends JPanel {
        private final int numero;
        Casa(int numero) {
            this.numero = numero;
            setOpaque(false);
            setPreferredSize(new Dimension(106, 78));
            setToolTipText(legenda(numero));
        }
        @Override protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth() - 2, h = getHeight() - 2;
            Color cor = numero == ConfiguracaoNovembro.ULTIMA_CASA ? META
                    : JogoNovembro.obterEfeito(numero) > 0 ? AVANCO
                    : JogoNovembro.obterEfeito(numero) < 0 ? RECUO : NORMAL;
            g.setColor(cor);
            g.fillRoundRect(1, 1, w, h, 17, 17);
            g.setColor(new Color(255, 255, 255, 72));
            g.setStroke(new BasicStroke(1.4f));
            g.drawRoundRect(1, 1, w, h, 17, 17);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString(String.format("%02d", numero), 12, 28);
            g.setFont(new Font("SansSerif", Font.BOLD, 11));
            g.setColor(new Color(238, 245, 255));
            if (numero == 0) g.drawString("INÍCIO", 12, 45);
            if (numero == ConfiguracaoNovembro.ULTIMA_CASA) g.drawString("META", 12, 45);
            if (JogoNovembro.obterEfeito(numero) == 4) g.drawString("+4 CASAS", 12, 45);
            if (JogoNovembro.obterEfeito(numero) == -4) g.drawString("-4 CASAS", 12, 45);

            int pontos = 0;
            for (int i = 0; i < jogadores.size(); i++) {
                if (jogadores.get(i).getPosicao() == numero) {
                    int x = 13 + pontos * 20;
                    int y = h - 16;
                    g.setColor(new Color(10, 19, 39));
                    g.fillOval(x-2, y-2, 17, 17);
                    g.setColor(corJogador(i));
                    g.fillOval(x, y, 13, 13);
                    pontos++;
                }
            }
            g.dispose();
        }
    }

    private static String legenda(int casa) {
        if (casa == 0) return "Casa 0 — início";
        if (casa == ConfiguracaoNovembro.ULTIMA_CASA) return "Casa 30 — chegada exata";
        int efeito = JogoNovembro.obterEfeito(casa);
        if (efeito != 0) return "Casa " + casa + " — " + (efeito > 0 ? "+" : "") + efeito + " casas";
        return "Casa " + casa;
    }
}
