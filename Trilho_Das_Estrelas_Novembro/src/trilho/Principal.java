/* Module and purpose: Principal — ponto de entrada único da versão jogável Swing de novembro
 * e demonstração de consola inspirada na versão de outubro.
 * Integração assistida por ChatGPT; ver AI_USE_NOVEMBRO.md.
 * Responsáveis/revisores e contributos efetivos: a confirmar pelo grupo.
 */
package trilho;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import trilho.novembro.JanelaNovembro;

/** Executar sem argumentos para jogar; passar --outubro para ver a demonstração simples. */
public class Principal {
    public static void main(String[] args) {
        if (args != null && args.length > 0 && "--outubro".equalsIgnoreCase(args[0])) {
            demonstrarOutubro();
        } else {
            iniciarSwing();
        }
    }

    private static void iniciarSwing() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // O Swing funciona com o aspeto predefinido.
            }
            new JanelaNovembro().setVisible(true);
        });
    }

    /** Mantém a demonstração de movimento do antigo Principal, sem ficheiros de outubro. */
    private static void demonstrarOutubro() {
        String[] nomes = {"André", "Tomás", "Isaias"};
        int[] posicoes = {0, 0, 0};
        int[] dados = {3, 5, 2, 4, 6, 1};
        int turno = 0;
        System.out.println("=== TRILHO DAS ESTRELAS - PROTOTIPO DE OUTUBRO ===");
        mostrarEstado(nomes, posicoes, turno);
        for (int dado : dados) {
            int anterior = posicoes[turno];
            System.out.println("\nVez de " + nomes[turno] + ": dado = " + dado);
            if (anterior + dado <= 30) {
                posicoes[turno] = anterior + dado;
                System.out.println(nomes[turno] + " avanca da casa " + anterior
                        + " para a casa " + posicoes[turno] + ".");
            } else {
                System.out.println(nomes[turno] + " nao avanca (ultrapassaria a casa 30).");
            }
            if (posicoes[turno] == 30) {
                System.out.println("Vencedor: " + nomes[turno]);
                mostrarEstado(nomes, posicoes, -1);
                break;
            }
            turno = (turno + 1) % nomes.length;
            mostrarEstado(nomes, posicoes, turno);
        }
        System.out.println("\nFim da demonstracao. Os efeitos especiais serao implementados mais tarde.");
    }

    private static void mostrarEstado(String[] nomes, int[] posicoes, int turno) {
        for (int i = 0; i < nomes.length; i++) {
            System.out.println(nomes[i] + ": casa " + posicoes[i]);
        }
        if (turno >= 0) {
            System.out.println("Proxima vez: " + nomes[turno]);
        }
    }
}
