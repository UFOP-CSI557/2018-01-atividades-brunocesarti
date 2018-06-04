/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import metodo.DEReal;
import problema.Problema;
import problema.ProblemaDeJong;
import problema.ProblemaRastrigin;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class DERealPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Double minimo = -100.0;
        Double maximo = 100.0;

        int D = 100;    // numero de variaveis
        Problema problema = new ProblemaRastrigin(D);

        int gmax = 300;     // criterio de parada - geracoes
        int Np = 30;    // tamanho da populacao
        double F = 0.00005;   // Coeficiente de mutacao
        double Cr = 0.8;    //Coeficiente de Crossover

        DEReal deReal1 = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
        DEReal deReal2 = new DEReal(minimo, maximo, problema, gmax, D, 2*Np, F, Cr);

        Individuo resultado = null;

        System.out.println("Execucao;Caso;Fo;Tempogasto;");

        for (int cases = 1; cases <= 30; cases++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1:
                        resultado = deReal1.executar();
                        break;
                    case 2:
                        resultado = deReal2.executar();
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(cases + ";" + i + ";" + resultado.getFuncaoObjetivo() + ";" + totalTime + ";");
                System.out.flush();

            }

        }

    }

}
