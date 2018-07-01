/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrealProvaFinal;

import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class AlgoritmoGenetico {

    // Tamanho da população
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de mutação - operador secundário
    Double pMutacao;
    // Critério de parada - número de gerações
    Integer geracoes;

    // Dados do problema
    // Problema - Rastrigin
    Problema problema;
    // Mínimo
    Double minimo;
    // Máximo
    Double maximo;
    // Variáveis
    Integer nVariaveis;
    // Casos
    Integer caso;

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Double minimo, Double maximo, Integer nVariaveis, Integer caso) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
        this.caso = caso;
    }

    public Individuo getMelhorSolucao() {
        return this.melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    public Double executar() {

        populacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);

        // Criar a população
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;

        // Enquanto o critério de parada não for satisfeito - Gerações
        for (int g = 1; g <= geracoes; g++) {

            for (int i = 0; i < this.tamanho; i++) {
                // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a operação

                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    Individuo desc1 = new Individuo(minimo, maximo, nVariaveis);
                    Individuo desc2 = new Individuo(minimo, maximo, nVariaveis);

                    // Progenitores
                    Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2);

                    // Ponto de corte
                    int corte = rnd.nextInt(p1.getVariaveis().size());

                    if (caso == 1) {

                        // Descendente 1 -> Ind1_1 + Ind2_2;
                        crossoverUmPonto(p1, p2, desc1, corte);

                        // Descendente 2 -> Ind2_1 + Ind1_2;
                        crossoverUmPonto(p2, p1, desc2, corte);
                    } else if (caso == 2) {
                        crossoverDoisPontos(p1, p2, desc1);
                        crossoverDoisPontos(p2, p1, desc2);
                    }
                    // Mutação
                    // Descendente 1
                    mutacaoPorVariavel(desc1);
                    // Descendente 2
                    mutacaoPorVariavel(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            // Ordenar populacao;
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao.getIndividuos()
                    .subList(this.tamanho,
                            populacao.getIndividuos().size())
                    .clear();

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            //Imprimir a situacao atual
//            System.out.println("Gen = " + g +
//                    "\tCusto = "
//                    + populacao.getIndividuos().get(0).getFuncaoObjetivo());
        }

//        System.out.println("Melhor resultado: ");
//        System.out.println(populacao.getIndividuos().get(0).getVariaveis());
//        
        // Recuperar o melhor individuo
        melhorSolucao = (Individuo) populacao.getIndividuos().get(0);
        
//        System.out.println("melhor ->"+ melhorSolucao.getFuncaoObjetivo() + " | ");

        return populacao.getIndividuos().get(0).getFuncaoObjetivo();

    }

    private void crossoverDoisPontos(Individuo pai1, Individuo pai2, Individuo filho) {
        Random rnd = new Random();
        int cInicial = rnd.nextInt(pai1.getVariaveis().size() / 2);
        int cFinal;

        do {
            cFinal = rnd.nextInt(pai1.getVariaveis().size());
        } while (cFinal > cInicial);

        filho.getVariaveis().clear();
        filho.getVariaveis().addAll(pai1.getVariaveis());

        for (int i = cInicial; i < cFinal; i++) {
            filho.getVariaveis().set(i, pai2.getVariaveis().get(i));
        }

    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Double valor = alpha * ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            Double valor = (1.0 - alpha) * ind2.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

    }

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getVariaveis().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {

                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = individuo.getVariaveis().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getVariaveis().set(i, valor);

                }

            }
        }

    }

}
