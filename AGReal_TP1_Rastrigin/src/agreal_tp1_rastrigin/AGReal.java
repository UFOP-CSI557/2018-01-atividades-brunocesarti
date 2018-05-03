/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal_tp1_rastrigin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author fernando
 */
public class AGReal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Problema problema = new Problema();

        Integer tamanho = 400;      // 400
        Double pCrossover = 0.9;    // 0.9
        Double pMutacao = 0.08;     // 0.8
        Integer geracoes = 300;     // 300
        
        Double minimo = -5.12;      // -5.12
        Double maximo = 5.12;       // 5.12
        Integer nVariaveis = 100;   // 100


            
        // Caso 1 -> crossoverUmPonto
        AlgoritmoGenetico ag1 = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis, 1);
        // Caso 2 -> crossoverDoisPontos
        AlgoritmoGenetico ag2 = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis, 2);

        Double resultado = 555.0;
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

                System.out.println("Execucao: "+cases + "; " + "Caso: Croosover "+  i + " ponto(s); " + "Resultado FO: " + resultado + "; " + " Tempo gasto:"+ totalTime);
                System.out.flush();

            }

        }
        

//        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
//        
//        ag.executar();
    }
    
}
