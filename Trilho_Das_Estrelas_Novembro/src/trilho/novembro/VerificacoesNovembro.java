/* Módulo autónomo de novembro — versão jogável.
 * Origem: implementação assistida por ChatGPT; rever, testar e registar contribuições reais em AI_USE_NOVEMBRO.md.
 */
package trilho.novembro;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/** Testes repetíveis independentes de Swing, sem dependências de outubro. */
public class VerificacoesNovembro {
    private static int total;
    private static int certas;

    private static void verificar(boolean condicao, String descricao) {
        total++;
        if (condicao) {
            certas++;
            System.out.println("OK: " + descricao);
        } else {
            System.out.println("FALHOU: " + descricao);
        }
    }

    private static boolean rejeitaNomes(List<String> nomes) {
        try { new JogoNovembro(nomes); return false; }
        catch (IllegalArgumentException ex) { return true; }
    }

    private static boolean rejeitaDado(JogoNovembro jogo, int valor) {
        try { jogo.jogarTurno(valor); return false; }
        catch (IllegalArgumentException ex) { return true; }
    }

    private static JogoNovembro dois() {
        return new JogoNovembro(Arrays.asList("André", "Tomás"));
    }
    private static JogoNovembro tres() {
        return new JogoNovembro(Arrays.asList("André", "Tomás", "Isaias"));
    }

    private static void jogarAlternado(JogoNovembro jogo, int... dadosDoPrimeiro) {
        for (int dado : dadosDoPrimeiro) {
            jogo.jogarTurno(dado);
            if (!jogo.isTerminado()) jogo.jogarTurno(1);
        }
    }

    public static void main(String[] args) {
        JogoNovembro inicial = tres();
        verificar(inicial.getJogadores().size() == 3, "tres jogadores iniciais");
        verificar(inicial.getJogadorAtual().getNome().equals("André"), "primeiro turno e do Andre");
        verificar(inicial.getJogadores().stream().allMatch(j -> j.getPosicao() == 0), "todos partem da casa zero");
        verificar(!inicial.isTerminado(), "jogo comeca por terminar");
        verificar(inicial.getVencedor() == null, "sem vencedor inicial");
        verificar(rejeitaNomes(null), "rejeita lista nula");
        verificar(rejeitaNomes(Arrays.asList("Um")), "rejeita um jogador");
        verificar(rejeitaNomes(Arrays.asList("a", "b", "c", "d", "e")), "rejeita cinco jogadores");
        verificar(rejeitaNomes(Arrays.asList("André", "  ")), "rejeita nome vazio");
        verificar(rejeitaNomes(Arrays.asList("Ana", "ana")), "rejeita nomes iguais ignorando maiusculas");
        verificar(rejeitaNomes(Arrays.asList("Ana", null)), "rejeita nome nulo");
        verificar(new JogoNovembro(Arrays.asList("1", "2", "3", "4")).getJogadores().size() == 4,
                "suporta quatro jogadores");
        verificar(new JogoNovembro(Arrays.asList(" Ana ", "Bruno")).getJogadores().get(0)
                .getNome().equals("Ana"), "limpa espacos de nomes");

        JogoNovembro v = tres();
        ResultadoNovembro r = v.jogarTurno(2);
        verificar(r.getPosicaoAnterior() == 0 && r.getPosicaoDado() == 2 && r.getPosicaoFinal() == 2,
                "avanco sem efeito");
        verificar(r.getEfeito() == 0 && !r.isUltrapassou(), "casa normal sem efeito");
        verificar(v.getJogadorAtual().getNome().equals("Tomás"), "turno passa ao segundo");
        v.jogarTurno(1);
        verificar(v.getJogadorAtual().getNome().equals("Isaias"), "turno passa ao terceiro");
        v.jogarTurno(1);
        verificar(v.getJogadorAtual().getNome().equals("André"), "turno volta ao primeiro");
        verificar(rejeitaDado(v, 0) && rejeitaDado(v, 7), "rejeita dados fora 1..6");
        verificar(v.getJogadorAtual().getNome().equals("André") && v.getJogadores().get(0).getPosicao() == 2,
                "dado invalido preserva turno e posicao");

        JogoNovembro bonus = dois();
        ResultadoNovembro b = bonus.jogarTurno(5);
        verificar(b.getPosicaoDado() == 5 && b.getPosicaoFinal() == 9 && b.getEfeito() == 4,
                "casa 5 avanca para 9");
        verificar(bonus.getJogadores().get(0).getPosicao() == 9, "estado de bonus atualizado");
        verificar(JogoNovembro.obterEfeito(9) == 0 && JogoNovembro.obterEfeito(7) == 0,
                "nao existem efeitos encadeados nos destinos");

        JogoNovembro recuo = dois();
        jogarAlternado(recuo, 6);
        ResultadoNovembro rr = recuo.jogarTurno(5);
        verificar(rr.getPosicaoDado() == 11 && rr.getPosicaoFinal() == 7 && rr.getEfeito() == -4,
                "casa 11 recua para 7");
        verificar(recuo.getJogadores().get(0).getPosicao() == 7, "estado de recuo atualizado");

        JogoNovembro avancar = dois();
        jogarAlternado(avancar, 6, 6, 6);
        ResultadoNovembro ra = avancar.jogarTurno(4);
        verificar(ra.getPosicaoDado() == 22 && ra.getPosicaoFinal() == 26 && ra.getEfeito() == 4,
                "casa 22 avanca para 26");
        verificar(avancar.getJogadores().get(0).getPosicao() == 26, "estado do avanco para 26");
        verificar(JogoNovembro.obterEfeito(14) == 0 && JogoNovembro.obterEfeito(19) == 0,
                "somente tres casas especiais");

        JogoNovembro meta = dois();
        jogarAlternado(meta, 6, 6, 6, 6, 5);
        verificar(meta.getJogadores().get(0).getPosicao() == 29, "alcanca a casa 29");
        ResultadoNovembro excedeu = meta.jogarTurno(2);
        verificar(excedeu.isUltrapassou() && excedeu.getPosicaoFinal() == 29,
                "ultrapassagem nao move a peca");
        verificar(meta.getJogadorAtual().getNome().equals("Tomás"), "ultrapassagem passa turno");
        meta.jogarTurno(1);
        ResultadoNovembro ganhou = meta.jogarTurno(1);
        verificar(ganhou.isVenceu() && meta.isTerminado(), "chegada exata termina a partida");
        verificar(meta.getVencedor() == meta.getJogadores().get(0), "guarda vencedor correto");
        verificar(meta.getJogadorAtual() == meta.getVencedor(), "turno final preserva vencedor");
        boolean semJogar = false;
        try { meta.jogarTurno(1); } catch (IllegalStateException ex) { semJogar = true; }
        verificar(semJogar && meta.getVencedor().getPosicao() == 30,
                "jogo terminado rejeita outros lancamentos");

        boolean semModificar = false;
        try { inicial.getJogadores().clear(); } catch (UnsupportedOperationException ex) { semModificar = true; }
        verificar(semModificar && inicial.getJogadores().size() == 3, "lista de jogadores so de leitura");
        verificar(new DadoManualNovembro(1).lancar() == 1 && new DadoManualNovembro(6).lancar() == 6,
                "dado manual aceita limites 1 e 6");
        boolean manualZero = false, manualSete = false, aleatorioNulo = false;
        try { new DadoManualNovembro(0); } catch (IllegalArgumentException ex) { manualZero = true; }
        try { new DadoManualNovembro(7); } catch (IllegalArgumentException ex) { manualSete = true; }
        try { new DadoAleatorioNovembro(null); } catch (IllegalArgumentException ex) { aleatorioNulo = true; }
        verificar(manualZero && manualSete, "dado manual rejeita limites invalidos");
        verificar(aleatorioNulo, "gerador aleatorio nao pode ser nulo");
        DadoAleatorioNovembro aleatorio = new DadoAleatorioNovembro(new Random(12345));
        boolean noIntervalo = true;
        for (int i = 0; i < 1000; i++) {
            int dado = aleatorio.lancar();
            if (dado < 1 || dado > 6) noIntervalo = false;
        }
        verificar(noIntervalo, "1000 lancamentos aleatorios no intervalo 1..6");

        System.out.println("Resultado novembro: " + certas + "/" + total);
        if (certas != total) System.exit(1);
    }
}
