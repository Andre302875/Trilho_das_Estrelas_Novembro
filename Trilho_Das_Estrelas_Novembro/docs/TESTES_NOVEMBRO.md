# Verificações iniciais da milestone de novembro

Executar `java -cp out trilho.novembro.VerificacoesNovembro` após compilar com as instruções de `README_NOVEMBRO.md`.

| Cenário | Entrada de teste | Esperado |
|---|---|---|
| Novo jogo | 3 jogadores | Casas 0, turno do primeiro |
| Validação | 1 ou 5 jogadores; nome nulo/vazio/repetido | Rejeitado |
| Turnos | Dados 2,1,1 | Primeiro → segundo → terceiro → primeiro |
| DadoNovembro inválido | 0 ou 7 | Posições e turno inalterados |
| Bónus 1 | Casa 0 + dado 5 | Casa 5 → 9 |
| Penalização | Casa 6 + dado 5 | Casa 11 → 7 |
| Bónus 2 | Casa 18 + dado 4 | Casa 22 → 26 |
| Sem encadeamento | Destinos 7, 9, 26 | Sem novo efeito |
| Ultrapassagem | Casa 29 + dado 2 | Continua na 29, passa a vez |
| Vitória | Casa 29 + dado 1 | Casa 30, termina e guarda vencedor |
| Após vitória | Outro lançamento | Rejeitado |
| DadoNovembro manual | 1 e 6; depois 0 e 7 | Aceita limites; rejeita valores inválidos |
| DadoNovembro aleatório | 1.000 lançamentos com semente fixa | Sempre entre 1 e 6 |

As verificações de outubro não estão incluídas neste ZIP; esta entrega contém apenas os testes de novembro. Os testes de novembro exercitam o motor; **não substituem** uma validação visual no computador do grupo (layout, cliques e diálogo de vitória).
