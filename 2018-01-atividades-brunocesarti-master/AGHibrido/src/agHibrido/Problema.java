/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agHibrido;

/**
 *
 * @author Bruno
 */
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double schwefel7;               
        Double soma = 0.0;
        Double xi = 0.0;
        
        schwefel7 = individuo.getnVar()*418.9829;
        
        for(int i =0; i < individuo.nVar; i++){//executar sem conversao direta
            xi = individuo.getVariaveis().get(i);
            soma += xi*(Math.sin(Math.sqrt(Math.abs(xi))));
        }
        schwefel7 = schwefel7 - soma;
//        System.out.println("FuncaoObjetivo:"+schwefel7);
                
        individuo.setFuncaoObjetivo(schwefel7);        
        
    }
    
}
