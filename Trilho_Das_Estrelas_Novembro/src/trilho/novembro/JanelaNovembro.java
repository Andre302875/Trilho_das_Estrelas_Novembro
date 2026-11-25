/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** A única classe que cria a interface e mostra mensagens; o motor não usa Swing. */
public class JanelaNovembro extends JFrame {
    private JogoNovembro jogo;
    private final DadoAleatorioNovembro dadoAleatorio = new DadoAleatorioNovembro();
    private final PainelTabuleiroNovembro tabuleiro = new PainelTabuleiroNovembro();
    private final JComboBox<ModoDadoNovembro> modo = new JComboBox<>(ModoDadoNovembro.values());
    private final JSpinner valorManual = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
    private final JLabel tituloTurno = new JLabel();
    private final JLabel ultimoDado = new JLabel("Último dado: —");
    private final JPanel listaJogadores = new JPanel();
    private final JTextArea mensagens = new JTextArea(7, 22);
    private final JButton jogar = new JButton("Jogar dado");

    public JanelaNovembro() {
        super("Trilho das Estrelas — Novembro");
        this.jogo = new JogoNovembro(Arrays.asList("André", "Tomás", "Isaias"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(960, 690));
        setSize(1100, 780);
        setLocationRelativeTo(null);
        configurarInterface();
        atualizarEstado();
        registar("Partida iniciada. Escolhe o dado e clica em Jogar dado.");
    }

    private void configurarInterface() {
        Color fundo = new Color(12, 22, 48);
        JPanel raiz = new JPanel(new BorderLayout(12, 12));
        raiz.setBackground(fundo);
        raiz.setBorder(new EmptyBorder(12, 12, 12, 12));
        setContentPane(raiz);
        raiz.add(new PainelArteNovembro(), BorderLayout.NORTH);

        JScrollPane deslocacao = new JScrollPane(tabuleiro);
        deslocacao.setBorder(BorderFactory.createEmptyBorder());
        deslocacao.getViewport().setBackground(fundo);
        raiz.add(deslocacao, BorderLayout.CENTER);

        JPanel direita = new JPanel();
        direita.setLayout(new BoxLayout(direita, BoxLayout.Y_AXIS));
        direita.setBackground(new Color(22, 40, 76));
        direita.setBorder(new EmptyBorder(20, 18, 18, 18));
        direita.setPreferredSize(new Dimension(290, 400));
        raiz.add(direita, BorderLayout.EAST);

        tituloTurno.setForeground(Color.WHITE);
        tituloTurno.setFont(new Font("SansSerif", Font.BOLD, 17));
        ultimoDado.setForeground(new Color(197, 222, 255));
        colocar(direita, tituloTurno);
        direita.add(Box.createVerticalStrut(12));
        colocar(direita, ultimoDado);
        direita.add(Box.createVerticalStrut(23));
        JLabel tituloJogadores = etiqueta("JOGADORES", true);
        colocar(direita, tituloJogadores);
        direita.add(Box.createVerticalStrut(8));
        listaJogadores.setOpaque(false);
        listaJogadores.setLayout(new BoxLayout(listaJogadores, BoxLayout.Y_AXIS));
        colocar(direita, listaJogadores);
        direita.add(Box.createVerticalStrut(20));

        colocar(direita, etiqueta("MODO DO DADO", true));
        direita.add(Box.createVerticalStrut(5));
        modo.setMaximumSize(new Dimension(260, 32));
        colocar(direita, modo);
        direita.add(Box.createVerticalStrut(8));
        colocar(direita, etiqueta("Valor manual (1 a 6):", false));
        valorManual.setMaximumSize(new Dimension(260, 30));
        colocar(direita, valorManual);
        direita.add(Box.createVerticalStrut(12));

        jogar.setFont(new Font("SansSerif", Font.BOLD, 16));
        jogar.setBackground(new Color(61, 177, 178));
        jogar.setForeground(new Color(11, 25, 49));
        jogar.setFocusPainted(false);
        jogar.setMaximumSize(new Dimension(260, 38));
        colocar(direita, jogar);
        direita.add(Box.createVerticalStrut(12));

        JButton nova = new JButton("Nova partida / jogadores");
        nova.setMaximumSize(new Dimension(260, 32));
        colocar(direita, nova);
        direita.add(Box.createVerticalStrut(8));
        JButton sair = new JButton("Sair");
        sair.setMaximumSize(new Dimension(260, 32));
        colocar(direita, sair);

        mensagens.setLineWrap(true);
        mensagens.setWrapStyleWord(true);
        mensagens.setEditable(false);
        mensagens.setBackground(new Color(18, 31, 59));
        mensagens.setForeground(new Color(231, 241, 255));
        mensagens.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JScrollPane caixaMensagens = new JScrollPane(mensagens);
        caixaMensagens.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(83, 127, 175)),
                "Histórico de jogadas", 0, 0,
                new Font("SansSerif", Font.BOLD, 12), Color.WHITE));
        caixaMensagens.setMaximumSize(new Dimension(260, 170));
        direita.add(Box.createVerticalStrut(16));
        colocar(direita, caixaMensagens);

        JLabel rodape = etiqueta("Especiais: 5 → 9   |   11 → 7   |   22 → 26   •   Vitória na casa 30", false);
        rodape.setBorder(new EmptyBorder(3, 9, 3, 9));
        raiz.add(rodape, BorderLayout.SOUTH);

        modo.addActionListener(e -> valorManual.setEnabled(modo.getSelectedItem() == ModoDadoNovembro.MANUAL));
        valorManual.setEnabled(false);
        jogar.addActionListener(e -> jogarDado());
        nova.addActionListener(e -> criarNovaPartida());
        sair.addActionListener(e -> dispose());
    }

    private static JLabel etiqueta(String texto, boolean negrito) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", negrito ? Font.BOLD : Font.PLAIN, 12));
        label.setForeground(negrito ? Color.WHITE : new Color(196, 222, 255));
        return label;
    }
    private static void colocar(JPanel panel, JComponent componente) {
        componente.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(componente);
    }

    private void jogarDado() {
        try {
            DadoNovembro dado;
            if (modo.getSelectedItem() == ModoDadoNovembro.MANUAL) {
                try {
                    valorManual.commitEdit();
                } catch (ParseException erro) {
                    throw new IllegalArgumentException("Escreve um número inteiro entre 1 e 6.");
                }
                dado = new DadoManualNovembro(((Number) valorManual.getValue()).intValue());
            } else {
                dado = dadoAleatorio;
            }
            ResultadoNovembro resultado = jogo.jogarTurno(dado.lancar());
            ultimoDado.setText("Último dado: " + resultado.getValorDado());
            String texto = resultado.getNomeJogador() + " lançou " + resultado.getValorDado() + ": ";
            if (resultado.isUltrapassou()) {
                texto += "não avançou, pois ultrapassaria a casa 30.";
            } else {
                texto += resultado.getPosicaoAnterior() + " → " + resultado.getPosicaoDado();
                if (resultado.getEfeito() != 0) {
                    texto += " → " + resultado.getPosicaoFinal() + " (efeito "
                            + (resultado.getEfeito() > 0 ? "+" : "") + resultado.getEfeito() + ").";
                } else texto += ".";
            }
            registar(texto);
            if (resultado.isVenceu()) {
                registar("VITÓRIA: " + resultado.getNomeJogador() + " chegou à casa 30!");
                JOptionPane.showMessageDialog(this,
                        "Parabéns, " + resultado.getNomeJogador() + "! Chegaste à casa 30.",
                        "Fim da partida", JOptionPane.INFORMATION_MESSAGE);
            }
            atualizarEstado();
        } catch (IllegalArgumentException | IllegalStateException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(),
                    "Jogada inválida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void criarNovaPartida() {
        JSpinner quantidade = new JSpinner(new SpinnerNumberModel(jogo.getJogadores().size(), 2, 4, 1));
        JTextField[] nomes = new JTextField[4];
        JPanel formulario = new JPanel(new GridLayout(5, 2, 8, 8));
        formulario.add(new JLabel("Número de jogadores:"));
        formulario.add(quantidade);
        for (int i = 0; i < 4; i++) {
            String sugerido = i < jogo.getJogadores().size()
                    ? jogo.getJogadores().get(i).getNome() : "Jogador " + (i + 1);
            nomes[i] = new JTextField(sugerido, 14);
            formulario.add(new JLabel("Jogador " + (i + 1) + ":"));
            formulario.add(nomes[i]);
        }
        Runnable atualizar = () -> {
            for (int i = 0; i < 4; i++) nomes[i].setEnabled(i < (int) quantidade.getValue());
        };
        quantidade.addChangeListener(e -> atualizar.run());
        atualizar.run();
        int resposta = JOptionPane.showConfirmDialog(this, formulario, "Nova partida",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resposta != JOptionPane.OK_OPTION) return;

        List<String> lista = new ArrayList<>();
        for (int i = 0; i < (int) quantidade.getValue(); i++) lista.add(nomes[i].getText());
        try {
            JogoNovembro novo = new JogoNovembro(lista);
            jogo = novo;
            ultimoDado.setText("Último dado: —");
            mensagens.setText("");
            registar("Nova partida com " + lista.size() + " jogadores.");
            atualizarEstado();
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage(),
                    "Jogadores inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarEstado() {
        tabuleiro.atualizar(jogo);
        tituloTurno.setText(jogo.isTerminado()
                ? "Venceu: " + jogo.getVencedor().getNome()
                : "Vez de: " + jogo.getJogadorAtual().getNome());
        jogar.setEnabled(!jogo.isTerminado());
        listaJogadores.removeAll();
        int indice = 0;
        for (JogadorNovembro jogador : jogo.getJogadores()) {
            boolean atual = !jogo.isTerminado() && jogador == jogo.getJogadorAtual();
            JLabel texto = new JLabel("● " + jogador.getNome() + " — casa " + jogador.getPosicao()
                    + (atual ? "  ←" : ""));
            texto.setFont(new Font("SansSerif", atual ? Font.BOLD : Font.PLAIN, 14));
            texto.setForeground(PainelTabuleiroNovembro.corJogador(indice++));
            colocar(listaJogadores, texto);
            listaJogadores.add(Box.createVerticalStrut(7));
        }
        listaJogadores.revalidate();
        listaJogadores.repaint();
    }

    private void registar(String mensagem) {
        mensagens.append(mensagem + "\n");
        mensagens.setCaretPosition(mensagens.getDocument().getLength());
    }
}
