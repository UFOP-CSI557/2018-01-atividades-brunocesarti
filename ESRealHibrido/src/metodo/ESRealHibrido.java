/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import java.util.Collections;
import java.util.Random;
import problema.Problema;
import solucao.Individuo;
import solucao.IndividuoDouble;
import solucao.PopulacaoDouble;

/**
 *
 * @author fernando
 */
public class ESRealHibrido implements Metodo {

    // Parametros - problema - DeJong
    private Double minimo;
    private Double maximo;
    private Integer nVariaveis;
    private Problema problema;

    // Parametros - ES
    private Integer mu; // Tamanho da populacao
    private Integer lambda; // numero de descendentes
    private Integer geracoes; // criterio de parada
    private Double pMutacao; // mutacao - aplicacao ao descendente - variacao/perturbacao
    private Integer caso;

    public ESRealHibrido(Double minimo, Double maximo, Integer nVariaveis, Problema problema, Integer mu, Integer lambda, Integer geracoes, Double pMutacao, Integer caso) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
        this.problema = problema;
        this.mu = mu;
        this.lambda = lambda;
        this.geracoes = geracoes;
        this.pMutacao = pMutacao;
        this.caso = caso;
    }

    @Override
    public Individuo executar() {

//        IndividuoDouble filho = new IndividuoDouble(minimo, maximo, mu);
        // Geracao da populacao inicial - aleatoria - tamanho mu
        PopulacaoDouble populacao = new PopulacaoDouble(problema, minimo, maximo, nVariaveis, mu);
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Populacao - descendentes
        PopulacaoDouble descendentes = new PopulacaoDouble();

        Random rnd = new Random();

        // Criterio de parada - numero de geracoes
        for (int g = 1; g <= this.geracoes; g++) {

            // Para cada individuo, gerar lambda/mu descendentes
            for (int i = 0; i < populacao.getIndividuos().size() - 1; i++) {

                // Gerar lambda/mu descendentes
                for (int d = 0; d < 2* (lambda / mu); d++) {
                    //PARTE HIBRIDA USANDO CROSSOVER DE 1 E 2 PONTOS NOS ESREAL
                    // Clonar individuo
                    IndividuoDouble p1 = (IndividuoDouble) populacao.getIndividuos().get(i).clone();

                    // Clonar individuo
                    IndividuoDouble p2 = (IndividuoDouble) populacao.getIndividuos().get(i + 1).clone();

                    IndividuoDouble filho = new IndividuoDouble(minimo, maximo, p1.getnVar());

                    // Ponto de corte
                    int corte = rnd.nextInt(p1.getCromossomos().size());

                    if (caso == 1) {
                        crossoverUmPonto(p1, p2, filho, corte);
                    } else if (caso == 2) {
                        crossoverDoisPontos(p1, p2, filho);
                    }
                    // Aplicar mutacao
                    mutacaoPorVariavel(filho);
                    // Avaliar
                    problema.calcularFuncaoObjetivo(filho);

                    // Inserir na populacao de descendentes
                    descendentes.getIndividuos().add(filho);

                }

            }

            // ES(mu, lambda)?
            // Eliminar a populacao corrente
            //populacao.getIndividuos().clear();
            // ES(mu + lambda)?
            // Mu + Lambda
            populacao.getIndividuos().addAll(descendentes.getIndividuos());
            // Ordenar Mu+Lambda
            Collections.sort(populacao.getIndividuos());
            // Definir sobreviventes
            populacao.getIndividuos()
                    .subList(this.mu, populacao.getIndividuos().size()).clear();
            // Limpar descendentes
            descendentes.getIndividuos().clear();

//            System.out.println("G = " + g 
//                    + "\t"
//                    + populacao.getMelhorIndividuo().getFuncaoObjetivo());
        }

        // Retornar o melhor individuo
        return populacao.getMelhorIndividuo();

    }

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getCromossomos().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {

                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = (Double) individuo.getCromossomos().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getCromossomos().set(i, valor);

                }

            }
        }

    }

    private void crossoverDoisPontos(IndividuoDouble pai1, IndividuoDouble pai2, Individuo filho) {
        Random rnd = new Random();
        int cInicial = rnd.nextInt(pai1.getCromossomos().size() / 2);
        int cFinal;

        do {
            cFinal = rnd.nextInt(pai1.getCromossomos().size());
        } while (cFinal > cInicial);

        filho.getCromossomos().clear();
        filho.getCromossomos().addAll(pai1.getCromossomos());

        for (int i = cInicial; i < cFinal; i++) {
            filho.getCromossomos().set(i, pai2.getCromossomos().get(i));
        }

    }

    private void crossoverUmPonto(IndividuoDouble ind1, Individuo ind2, IndividuoDouble descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();
        Double valor = 0.0;

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            valor = alpha * ind1.getCromossomos().get(i);
            descendente.getCromossomos().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            valor = (1.0 - alpha) * (double) ind2.getCromossomos().get(i);
            descendente.getCromossomos().add(valor);
        }

    }

}
