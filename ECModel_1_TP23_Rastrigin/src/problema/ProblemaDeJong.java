/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema;

import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class ProblemaDeJong implements Problema {

    private int dimensao;
    
    public ProblemaDeJong(int dimensao) {
        this.dimensao = dimensao;
    }
    
    @Override
    public void calcularFuncaoObjetivo(Individuo individuo) {
        Double soma = 0.0;
        
        for(Object var : individuo.getCromossomos()) {
            Double vr = (Double)var;
            soma +=  Math.pow(vr, 2);
        }
        
        individuo.setFuncaoObjetivo(soma);      }

    @Override
    public int getDimensao() {
        return this.dimensao;
    }
    
}
