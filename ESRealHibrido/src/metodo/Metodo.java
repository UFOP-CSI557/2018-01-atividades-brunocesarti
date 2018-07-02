/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo;

import solucao.Individuo;

/**
 *
 * @author fernando
 */
public interface Metodo {
    
    // Retorna o melhor indivíduo
    Individuo executar();
    
}
