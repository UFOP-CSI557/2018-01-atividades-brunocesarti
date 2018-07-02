/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esrealhibrido;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import metodo.ESRealHibrido;
import problema.Problema;
import problema.ProblemaSchwefel7;
import solucao.Individuo;

/**
 *
 * @author Bruno
 */
public class ESRealHibridoMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Double minimo = -500.0; // Minimo da funcao    DEFAULT
        Double maximo = 500.0;  // Maximo da funcao    DEFAULT
        Integer nVariaveis = 50; // Numero de Variaveis    DEFAULT
        
        Problema problema = new ProblemaSchwefel7(nVariaveis);  // Problema Schwefel 7    DEFAULT

        // Parametros - ESHibrido
        Integer mu = 100; // Tamanho da populacao   DEFAULT
        Integer lambda = 400; // numero de descendentes
        Integer geracoes = 300; // criterio de parada   DEFAULT
        Double pMutacao = 0.008; // mutacao - aplicacao ao descendente - variacao/perturbacao

        ESRealHibrido esh1 = new ESRealHibrido(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao, 1);
       
        // Variando dados para segunda instancia
        lambda = 800;
        pMutacao = 0.003;
        
        ESRealHibrido esh2 = new ESRealHibrido(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao, 2);

        Individuo melhor = null;
                
        System.out.println("NumeroDaExecucao;Caso;ResultadoDaFo;TempoDeExecucaoEmMilessegundos;");
       
        for (int cases = 1; cases <= 30; cases++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (Integer i : casos) {
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 1: 
                        melhor = esh1.executar();
                        break;
                    case 2:
                        melhor = esh2.executar();
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(cases+";"+i+";"+melhor.getFuncaoObjetivo()+";"+totalTime+";");
                System.out.flush();
            }

        }
        
        
    }
}