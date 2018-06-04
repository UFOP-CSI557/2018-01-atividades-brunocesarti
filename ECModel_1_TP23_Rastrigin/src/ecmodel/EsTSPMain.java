/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import metodo.EsTSP;
import problema.Problema;
import problema.ProblemaTSP;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class EsTSPMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Problema problema = new ProblemaTSP("/Volumes/MacData/Dropbox/Dados/Desenvolvimento/Netbeans/CSI557-Computacao-Evolucionaria/Codes/instances/tsplib/berlin52.tsp");
        
        // Parametros = ES
        Integer mu = 20;
        Integer lambda = 100;
        Integer geracoes = 1000;
        Double pMutacao = 0.00;
        Double pBuscaLocal = 0.8;
        
        EsTSP esTsp = new EsTSP(problema, mu, lambda, geracoes, pMutacao, pBuscaLocal);
        Individuo melhor = esTsp.executar();
        
        System.out.println(melhor);
        
        
    }
    
}
