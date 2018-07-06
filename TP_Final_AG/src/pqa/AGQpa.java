package pqa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AGQPA {

//    ===========================================================================================================================
//                              Criterios da Kelly
//    static int tamanhoInstancia;
//    static ArrayList<ArrayList<Integer>> fluxo;
//    static ArrayList<ArrayList<Integer>> distancia;
//    static int mu;// numero de pais selecionados
//    static int lambda;// numero de descendentes
//    static long tempoExecucao;// numero de iteracoes
//    static double taxaMutacao; // probabilidade de se aplicar mutacao
//    static double taxaBuscaLocal;// probabilidade de se aplicar busca local
//    static int melhora;// numero de descendentes
//    ============================================================================================================================
    // FBO: Parâmetros via linha de comando?
    // GAP = (Ro - Rl) / Rl
    // FBO: Classe para o problema?
//    -----------------------------------------------------------------------------------------
//    static int tamanhoInstancia;
//    static ArrayList<ArrayList<Integer>> fluxo;
//    static ArrayList<ArrayList<Integer>> distancia;
//    static int mu;// numero de pais selecionados
//    static int lambda;// numero de descendentes
//    static long tempoExecucao;// numero de iteracoes
//    static double taxaMutacao; // probabilidade de se aplicar mutacao
//    static double taxaBuscaLocal;// probabilidade de se aplicar busca local
//    static int melhora;// numero de descendentes
//
//    static ArrayList<Individuo> populacaoInicial = new ArrayList<Individuo>();
//    static ArrayList<Individuo> melhoresIndividuos = new ArrayList<Individuo>();
//    ------------------------------------------------------------------------------------------
    static int tamanhoInstancia;
    static ArrayList<ArrayList<Integer>> fluxo;
    static ArrayList<ArrayList<Integer>> distancia;
//    static int mu;// numero de pais selecionados
//    static int lambda;// numero de descendentes

    static int numDescendentes;
    static int tamanhoPop;
    static long tempoExecucao;// numero de iteracoes
    static double taxaMutacao; // probabilidade de se aplicar mutacao
    static double taxaCrossover;// probabilidade de se aplicar busca local
    static int geracoes;// numero de descendentes

    static ArrayList<Individuo> populacaoInicial = new ArrayList<Individuo>();
    static ArrayList<Individuo> melhoresIndividuos = new ArrayList<Individuo>();

//            ag = new AGQPA(fluxo, distancia, tamanhoInstancia, numDescendentes, tamanhoPop, parada, pMutacao, pCrossover,geracoes);
    public AGQPA(ArrayList<ArrayList<Integer>> fluxo, ArrayList<ArrayList<Integer>> distancia, int tamanhoInstancia,
            int numDescendentes, int tamanhoPop, long tempoExecucao, double taxaMutacao, double taxaCrossover, int geracoes) {
        super();
        AGQPA.fluxo = fluxo;
        AGQPA.distancia = distancia;
        AGQPA.tamanhoInstancia = tamanhoInstancia;
        AGQPA.numDescendentes = numDescendentes;
        AGQPA.tamanhoPop = tamanhoPop;
        AGQPA.tempoExecucao = tempoExecucao;
        AGQPA.taxaMutacao = taxaMutacao;
        AGQPA.taxaCrossover = taxaCrossover;
        AGQPA.geracoes = geracoes;
    }

    public static Individuo gerarSolucao() {

        populacaoInicial.clear();
        melhoresIndividuos.clear();
        // metodo responsavel pela geracao da populacao inicial
        gerarPopulacaoInicial();

        int iterador = 0;

        // ordenar populacao inicial pelo fitness
        Collections.sort(populacaoInicial);
//        System.out.println("Populacao Inicial gerada: " + populacaoInicial.toString());
        // pega o melhor individuo na populacao inicial
        Individuo melhor = populacaoInicial.get(0);
//        System.out.println("Melhor individuo da Populacao Inicial gerada: " + melhor.toString());

        long start_time = System.currentTimeMillis();
//        long end_time = start_time + tempoExecucao * 60000; |- nesta escala cada prada = 1 equivale a 1 minuto, multiplicando por 6000 cada 10 no parada equivale a 1 minuto
        long end_time = start_time + tempoExecucao * 6000;

        while (System.currentTimeMillis() < end_time) {

            // FBO: Debug -> imprimir o valor da FO e do GAP ->
            // evoluÃ§Ã£o/convergÃªncia.
            // insere os melhores individuos da populacao inicial em um array
            for (int i = 0; i < numDescendentes; i++) {
//                System.out.println("gene selecionado: " + populacaoInicial.get(i));

                melhoresIndividuos.add(populacaoInicial.get(i));
            }

            // populacao inicial recebe os melhores individuos
            populacaoInicial.clear();
//            System.out.println("populacao no melhor inicial: " + melhoresIndividuos);

            for (Individuo ind : melhoresIndividuos) {
                populacaoInicial.add(Individuo.deepClone(ind));
//                System.out.println("populacao incremento AG: " + populacaoInicial);

            }
//            System.out.println("populacao no AG: " + populacaoInicial);

//            System.out.println("populacao antes do AG: " + populacaoInicial);

//=================================== parte da Heuristica Usando AG =======================================
            /*
			 * submete os melhores individuos ao processo de mutacao e crossover
			 * local e em seguida adiciona os individuos gerados na populacao
			 * inicial
             */
            int ind1, ind2;
            Random rnd = new Random();

            for (Individuo ind : melhoresIndividuos) {
                for (int i = 0; i < tamanhoInstancia; i++) {
                    // Crossover
                    if (rnd.nextDouble() <= taxaCrossover) {
                        // Realizar a operação
                        ind1 = rnd.nextInt(populacaoInicial.size());

                        do {
                            ind2 = rnd.nextInt(populacaoInicial.size());
                        } while (ind1 == ind2);

                        Individuo desc1 = new Individuo();
                        desc1.setGenes(new LinkedList<Integer>());

                        Individuo desc2 = new Individuo();
                        desc2.setGenes(new LinkedList<Integer>());

                        // Progenitores
                        Individuo p1 = populacaoInicial.get(ind1);
                        Individuo p2 = populacaoInicial.get(ind2);

                        // Ponto de corte
                        int corte = rnd.nextInt(p1.getGenes().size());

                        // Descendente 1 -> Ind1_1 + Ind2_2;
                        crossoverUmPonto(p1, p2, desc1, corte);

                        // Descendente 2 -> Ind2_1 + Ind1_2;
                        crossoverUmPonto(p2, p1, desc2, corte);

                        // Mutação
                        // Descendente 1
                        mutacaoSWAP(desc1);
                        // Descendente 2
                        mutacaoSWAP(desc2);

                        // Avaliar as novas soluções
                        desc1.setFitness(calculaCusto(desc1.getGenes()));
                        desc2.setFitness(calculaCusto(desc2.getGenes()));

                        BuscaLocalCombinatorio(desc1);
                        BuscaLocalCombinatorio(desc2);

                        // Inserir na nova população
                        populacaoInicial.add(desc1);
                        populacaoInicial.add(desc2);
//                        System.out.println("populacao no AG: " + populacaoInicial);

                    }

                }
            }

//==================================== Fim parte Heuristica Usando AG =======================================  
            melhoresIndividuos.clear();

            // ordenar populacao inicial pelo fitness
            Collections.sort(populacaoInicial);
//            System.out.println("teste gerar populacao: " + populacaoInicial);

            // pega o melhor individuo na populacao inicial
            melhor = populacaoInicial.get(0);
            System.out.println("Melhor (" + System.currentTimeMillis() + "): " + melhor.toString());

        }

        return melhor;
    }

    // gera populacao inicial
    private static void gerarPopulacaoInicial() {

        // FBO:
        // - gerar array com o tamanho da instância - 1-n;
        // - usar Collections.shuffle() para embaralhar o array
        LinkedList<Integer> genes = new LinkedList<Integer>();
//                    System.out.println("teste gerar populacao: "+ tamanhoInstancia + "nDesc:"+numDescendentes);

        for (int i = 1; i <= tamanhoInstancia; i++) {
            genes.add(i);
        }

        for (int i = 0; i < numDescendentes; i++) {

            // gera genes aleatoriamente e entao os insere dentro do arraylist
            Collections.shuffle(genes);

            // cria um novo individuo e o insere na populacao inicial
            Individuo individuo = new Individuo();
//            System.out.println("individuo " + i + ": " + genes);

            individuo.setGenes(genes);
            individuo.setFitness(calculaCusto(genes));
            populacaoInicial.add(individuo);
        }
    }

    public static void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover OX
        // Copiar Parte 1 do Ind1
        descendente.getGenes().addAll(ind1.getGenes().subList(0, corte));

        int tam = descendente.getGenes().size();
        int i = corte;

        // Completa a partir da Parte 2 do Individuo 2
        // Se necessário, a Parte 1 do Indivíduo 2 - até todas as cidades serem visitadas
        while (tam < tamanhoInstancia) {

            if (!descendente.getGenes().contains(ind2.getGenes().get(i))) {
                descendente.getGenes().add(ind2.getGenes().get(i));
                tam++;

                if (tam == tamanhoInstancia) {
                    break;
                }

            }

            i++;

            if (i == tamanhoInstancia) {
                i = 0;
            }

        }

    }

    public static Individuo mutacaoSWAP(Individuo individuo) {

        Random rnd = new Random();

        // Verificar a troca para cada cidade - posição
        for (int i = 0; i < individuo.getGenes().size(); i++) {
            if (rnd.nextDouble() <= taxaMutacao) {

                // Mutacao SWAP - troca entre duas cidades
                int j;
                do {
                    j = rnd.nextInt(tamanhoInstancia);
                } while (i == j);

                Collections.swap(individuo.getGenes(), i, j);

            }
        }

        return individuo;
    }

    private static Individuo BuscaLocalCombinatorio(Individuo vizinho) {
        Individuo ind;
        Random r = new Random();
        int index_u = r.nextInt(tamanhoInstancia - 1);
        int index_v = r.nextInt(tamanhoInstancia - 1);

        while (index_u == index_v || (Math.abs(index_u - index_v) == 1) || index_u == (tamanhoInstancia - 1)
                || index_v == (tamanhoInstancia - 1)) {
            index_u = r.nextInt(tamanhoInstancia - 1);
            index_v = r.nextInt(tamanhoInstancia - 1);
        }

        List<Integer> ordemMovimentos = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Collections.shuffle(ordemMovimentos);

        Movimento m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
        long iterador;
        for (Integer integer : ordemMovimentos) {
            switch (integer) {
                case 1:
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento1();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }

                    break;
                case 2:
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento2();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }
                    break;
                case 3:
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento3();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }
                    break;
                case 4:
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento4();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }
                    break;
                case 5:
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento5();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }
                    break;
                case 6:
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento6();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }
                    break;
                case 7:
                    // http://www.technical-recipes.com/2017/applying-the-2-opt-algorithm-to-traveling-salesman-problems-in-java/
                    iterador = 0;
                    while (iterador < numDescendentes) {
                        m = new Movimento(Individuo.deepClone(vizinho), index_u, index_v);
                        ind = m.movimento7();
                        ind.setFitness(calculaCusto(ind.getGenes()));
                        if (vizinho.getFitness() > ind.getFitness()) {
                            vizinho = Individuo.deepClone(ind);
                            break;
                        }
                        iterador++;
                    }
                    break;
            }

        }
        return vizinho;
    }

    public static int calculaCusto(LinkedList<Integer> genes) {
        int custo = 0;

        for (int i = 0; i < genes.size(); i++) {
            for (int j = 0; j < genes.size(); j++) {
                custo = custo + fluxo.get(i).get(j) * distancia.get(genes.get(i) - 1).get(genes.get(j) - 1);
            }

        }
        return custo;
    }

}
