/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrealProvaFinal;

/**
 *
 * @author fernando
 */
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double schwefel7;               
        Double soma = 0.0;
        
        schwefel7 = individuo.getnVar()* 418.9829;
        
        for(int i =0; i < individuo.nVar; i++){//executar sem conversao direta
            soma += individuo.getVariaveis().get(i)*(
                    Math.sin(
                    Math.sqrt(
                            Math.abs(
                                    individuo.getVariaveis().get(i)
                            )
                    )
            )
                    );
        }
        schwefel7 = schwefel7 - soma;
//        System.out.println("FuncaoObjetivo:"+schwefel7);
        
//        System.out.println("rastrigin_soma-> "+rastringin_soma+ " | ");
        
        individuo.setFuncaoObjetivo(schwefel7);        
        
        
        
        
        
//        Double soma = 0.0;
//                
//        for( Double var : individuo.getVariaveis() ) {
//            soma += Math.pow(var, 2);
//        }
//        
//        individuo.setFuncaoObjetivo(soma);
        
    }
    
}
