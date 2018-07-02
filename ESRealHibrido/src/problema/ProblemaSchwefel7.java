/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema;

import solucao.Individuo;

/**
 *
 * @author Bruno
 */
public class ProblemaSchwefel7 implements Problema {

     private Integer nVariaveis;

    public ProblemaSchwefel7(Integer nVariaveis) {
        this.nVariaveis = nVariaveis;
    }
        
    @Override
    public void calcularFuncaoObjetivo(Individuo individuo) {

        Double schwefel7;               
        Double soma = 0.0;
        Double xi = 0.0;
        
        schwefel7 = individuo.getCromossomos().size()*418.9829;
        
        for(int i =0; i < individuo.getCromossomos().size(); i++){//executar sem conversao direta
            xi = (double)individuo.getCromossomos().get(i);
            soma += xi*(Math.sin(Math.sqrt(Math.abs(xi))));
        }
        schwefel7 = schwefel7 - soma;
//        System.out.println("FuncaoObjetivo:"+schwefel7);
                
        individuo.setFuncaoObjetivo(schwefel7); 
        
    }

    @Override
    public int getDimensao() {
        return this.nVariaveis;
    }

}
