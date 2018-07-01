/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agHibrido;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Bruno
 */
public class AGHibrido {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Problema problema = new Problema();

        Integer tamanho = 100;     // 100 tamanho da populacao 
        Double pCrossover = 0.9;   // 0.9
        Double pMutacao = 0.5;     // 0.8
        Integer geracoes = 300;    // 300 numero de gerações
        
        Double minimo = -500.0;    // -500.0 intervalo    
        Double maximo = 500.0;     // 500.0 intervalo
        Integer nVariaveis = 50;   // 50 numero de variáveis


            
        // Caso 1 -> crossoverUmPonto
        AlgoritmoGenetico ag1 = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis, 1);
        // Caso 2 -> crossoverDoisPontos
        AlgoritmoGenetico ag2 = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis, 2);

        Double resultado = 555.0;
        System.out.println("Execucao;Caso;Fo;Tempogasto;");

        for (int cases = 1; cases <= 30; cases++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1:
                        ag1.executar();
                        resultado = ag1.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                    case 2:
                        ag2.executar();
                        resultado = ag2.getMelhorSolucao().getFuncaoObjetivo();
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

//                System.out.println("Execucao: "+cases + "; " + "Caso: Croosover "+  i + " ponto(s); " + "Resultado FO: " + resultado + "; " + " Tempo gasto:"+ totalTime);
//                    System.out.println("Execucao: "+cases+"->"+i+"; "+totalTime+";");

            System.out.println(cases+";"+i+";"+resultado+";"+totalTime+";");
            System.out.flush();

            }

        }
        

//        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
//        
//        ag.executar();
    }
    
}
