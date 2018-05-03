/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agreal_tp1_rastrigin;

/**
 *
 * @author fernando
 */
public class Problema {
    
    public void calcularFuncaoObjetivo(Individuo individuo) {
        
        Double rastringin_soma;               
        Double soma = 0.0;
        
        rastringin_soma = individuo.getnVar()* 10.0;
        
        for(int i =0; i < individuo.nVar; i++)//executar sem conversao direta
            soma += Math.pow(individuo.getVariaveis().get(i),2) - 10*Math.cos(2*Math.PI*individuo.getVariaveis().get(i));
        
        rastringin_soma += soma;
        
//        System.out.println("rastrigin_soma-> "+rastringin_soma+ " | ");
        
        individuo.setFuncaoObjetivo(rastringin_soma);        
        
        
        
        
        
//        Double soma = 0.0;
//                
//        for( Double var : individuo.getVariaveis() ) {
//            soma += Math.pow(var, 2);
//        }
//        
//        individuo.setFuncaoObjetivo(soma);
        
    }
    
}
