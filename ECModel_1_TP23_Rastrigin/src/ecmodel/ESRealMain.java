/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import metodo.ESReal;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class ESRealMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Double minimo = -100.0;
        Double maximo = 100.0;
        Integer nVariaveis = 100;
        Problema problema = new ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 30; // Tamanho da populacao
        Integer lambda = 100; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.8; // mutacao - aplicacao ao descendente - variacao/perturbacao

        ESReal esReal1 = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        ESReal esReal2 = new ESReal(minimo, maximo, nVariaveis, problema, 2 * mu, lambda, geracoes, pMutacao);

        Individuo melhor = null;

        System.out.println("Execucao;Caso;Fo;Tempogasto;");

        for (int cases = 1; cases <= 30; cases++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1:
                        melhor = esReal1.executar();
                        break;
                    case 2:
                        melhor = esReal2.executar();
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(cases + ";" + i + ";" + melhor.getFuncaoObjetivo() + ";" + totalTime + ";");
                System.out.flush();

            }

        }

    }

}
