/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** Cabeçalho gráfico: usa o conceito visual quando o ficheiro estiver disponível. */
public class PainelArteNovembro extends JPanel {
    private final BufferedImage ilustracao;

    public PainelArteNovembro() {
        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(new File("imagens/CONCEITO_ARTISTICO_NOVEMBRO.png"));
        } catch (IOException ignored) {
            // A janela continua a funcionar fora da pasta do projeto.
        }
        ilustracao = imagem;
        setPreferredSize(new Dimension(980, 124));
    }

    @Override protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int largura = getWidth(), altura = getHeight();
        if (ilustracao != null) {
            // Corte central para manter proporções da arte original.
            double escala = Math.max((double) largura / ilustracao.getWidth(),
                    (double) altura / ilustracao.getHeight());
            int w = (int) Math.ceil(ilustracao.getWidth() * escala);
            int h = (int) Math.ceil(ilustracao.getHeight() * escala);
            g.drawImage(ilustracao, (largura-w)/2, (altura-h)/2, w, h, null);
        } else {
            g.setPaint(new GradientPaint(0, 0, new Color(31, 58, 120),
                    largura, altura, new Color(96, 41, 137)));
            g.fillRect(0, 0, largura, altura);
        }
        g.setColor(new Color(2, 8, 24, 164));
        g.fillRect(0, 0, largura, altura);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.setColor(Color.WHITE);
        g.drawString("TRILHO DAS ESTRELAS", 24, 53);
        g.setFont(new Font("SansSerif", Font.PLAIN, 14));
        g.setColor(new Color(218, 235, 255));
        g.drawString("Milestone de novembro  •  corrida espacial  •  2 a 4 jogadores", 26, 82);
        g.dispose();
    }
}
