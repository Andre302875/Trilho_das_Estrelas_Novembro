# Ponto de situação de novembro — progresso, reflexão e próximos passos

Este documento complementa as regras, o UML, o plano de testes e os resultados técnicos. Descreve o estado da versão atual de novembro. O grupo deve rever a reflexão e adaptá-la à sua experiência real antes da entrega.

## 2. Progresso atual

### Funcionalidades que já estão a funcionar

- A aplicação tem uma interface gráfica desenvolvida em Java Swing.
- É possível configurar uma partida para 2 a 4 jogadores. A configuração inicial utiliza os nomes André, Tomás e Isaias.
- Os jogadores jogam à vez e as posições dos peões são representadas no tabuleiro.
- Estão disponíveis os dois modos de lançamento do dado: aleatório e manual, com valores de 1 a 6.
- Estão implementadas as três casas especiais: a casa 5 faz avançar para a 9, a casa 11 faz recuar para a 7 e a casa 22 faz avançar para a 26.
- Os valores inválidos do dado e as configurações inválidas dos jogadores são rejeitados pela lógica do jogo.
- Para vencer, o jogador tem de chegar exatamente à casa 30. Se o lançamento ultrapassar a casa 30, o peão fica na mesma posição e a vez passa ao jogador seguinte.
- A interface inclui opções para iniciar uma nova partida e sair do jogo.

### Testes e evidências

A classe de verificações repetíveis de novembro apresenta **40/40 testes aprovados**. O projeto foi compilado com compatibilidade com Java 11 (`javac --release 11`). A janela Swing também foi aberta num ambiente gráfico de teste. No modo manual, um lançamento de 5 fez o primeiro jogador avançar da casa 0 para a casa 5 e, devido ao efeito especial, para a casa 9; depois, a vez passou ao jogador seguinte. O modo aleatório também foi experimentado.

Estes resultados não significam que todos os aspetos tenham sido demonstrados nos computadores dos alunos. Em particular, o grupo ainda deve realizar e registar uma partida completa através da interface gráfica, até aparecer o vencedor.

### O que está incompleto ou ainda precisa de confirmação

- Executar o projeto nos computadores dos três elementos do grupo e confirmar a configuração do JDK e a apresentação da interface Swing.
- Jogar uma partida completa através da interface gráfica, incluindo a condição de vitória, e guardar evidências.
- Verificar os controlos de nova partida e de saída no ambiente real de cada aluno.
- Rever a disposição da interface e as janelas de introdução de dados nos tamanhos de ecrã utilizados na apresentação.
- Manter o histórico do GitHub atualizado e registar os contributos reais de implementação, testes e revisão de cada elemento.

### Como executar a versão atual

1. Extrair o ficheiro ZIP e abrir a pasta `Trilho_Das_Estrelas_Novembro` no IntelliJ IDEA.
2. Em **File → Project Structure → Project SDK**, selecionar JDK 11 ou superior.
3. Se necessário, marcar a pasta `src` como **Sources Root**.
4. Abrir `src/trilho/Principal.java`.
5. Executar `Principal.main()` através do ícone verde de execução. O jogo Swing será aberto por predefinição.

Para compilar e executar através do terminal, na pasta do projeto:

```sh
javac --release 11 -encoding UTF-8 -d out src/trilho/Principal.java src/trilho/novembro/*.java
java -cp out trilho.Principal
```

Para executar as verificações repetíveis depois da compilação:

```sh
java -cp out trilho.novembro.VerificacoesNovembro
```

## 4. Reflexão e próximos passos

### Dificuldade encontrada e forma de resolução

Uma das dificuldades deste projeto foi manter as regras do jogo separadas da interface gráfica Swing. Se as regras de movimento e a gestão das jogadas forem colocadas diretamente nos eventos dos botões, o código torna-se mais difícil de testar e de manter. Para resolver esta dificuldade, a equipa organizou o estado e as regras de movimento na classe `JogoNovembro`, representou os jogadores e os resultados das jogadas em classes próprias e deixou a interação gráfica a cargo de `JanelaNovembro` e dos painéis do tabuleiro. Esta separação também permitiu realizar testes repetíveis à lógica do jogo sem ser necessário utilizar a interface gráfica.

**Nota para o grupo:** esta reflexão é uma sugestão baseada na estrutura atual do código. Cada elemento deve adaptá-la para descrever uma dificuldade que tenha realmente encontrado e o seu contributo individual.

### O que falta fazer antes da entrega final

- Completar uma partida através da interface Swing e guardar uma captura de ecrã com o vencedor.
- Testar os dois modos de dado e a rejeição de valores inválidos diretamente na interface gráfica, e não apenas nos testes automáticos do modelo.
- Corrigir eventuais problemas encontrados nos computadores do grupo.
- Rever o código, os comentários, o UML, as regras e a declaração de utilização de IA para garantir que correspondem à implementação final.
- Completar o registo de contributos com o trabalho efetivamente realizado por André, Tomás e Isaias.
- Continuar a fazer commits relevantes no GitHub e preparar a demonstração e o relatório final.

## Dúvida a esclarecer com o professor

**Quando um peão cai numa casa especial e é deslocado para outra casa, o efeito da casa de destino também deve ser ativado durante a mesma jogada, ou deve ser aplicado apenas o primeiro efeito especial?**

Na implementação atual, o grupo optou por **não encadear efeitos**: durante essa jogada, só é aplicado o efeito da primeira casa especial em que o peão cai. Esta decisão está descrita nas regras e é verificada nos testes. A dúvida pode ser colocada ao professor para confirmar se esta interpretação corresponde ao pretendido para o projeto.

---

*Antes da entrega, o grupo deve personalizar a reflexão e confirmar que as tarefas ainda por realizar correspondem ao estado real do projeto.*
