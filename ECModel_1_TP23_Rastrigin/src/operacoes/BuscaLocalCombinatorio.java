/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import problema.Problema;
import solucao.Individuo;

/**
 *
 * @author fernando
 */
public class BuscaLocalCombinatorio {
    
    Problema problema;

    //1) remover u e inserir após v;
    //2) remover u e x e inserir u e x após v;
    //3) remover u e x e inserir x e u após v; (posições invertidas)
    //4) trocar u e v;
    //5) troca u e x com v;
    //6) troca u e x com v e y;
    
    public BuscaLocalCombinatorio(Problema problema) {
        this.problema = problema;
    }
    
    public void executar(Individuo individuo) {
        
        // Movimentos/operacoes
        ArrayList<Integer> operacoes = new ArrayList<>( Arrays.asList(1, 2, 3, 4) );
        
        Collections.shuffle(operacoes);
        
        for(Integer i : operacoes) {
            
            switch(i) {
                
                case 1:
                    buscaLocalRemoverUV(individuo);
                    break;
                case 2:
                    buscaLocalRemoverUXaposV(individuo);
                    break;
                case 3: 
                    buscaLocalRemoverUXInserirXUaposV(individuo);
                    break;
                case 4:
                    buscaLocalSwap(individuo);
                    break;
                
            }
            
        }
        
    }
    
    //1) remover u e inserir após v;
    // Remover U e inserir após V
    public void buscaLocalRemoverUV(Individuo individuo) {
        
        Double foAtual = individuo.getFuncaoObjetivo();
        
        for(int u = 0; u < individuo.getCromossomos().size()-1; u++) {
            for(int v = u + 1; v < individuo.getCromossomos().size(); v++) {
                // Recuperar o valor na posição U
                Integer valorU = (Integer) individuo.getCromossomos().get(u);
                
                // Remover U
                individuo.getCromossomos().remove(u);
                // Inserir após V
                individuo.getCromossomos().add(v, valorU);
                
                // Calcular o Custo
                problema.calcularFuncaoObjetivo(individuo);
                
                // Se existe melhora
                if (individuo.getFuncaoObjetivo() < foAtual) {
                    // Encerrar - first improvement
                    return;
                } else {
                    // Desfazer a troca
                    // Remover de V
                    individuo.getCromossomos().remove(v);
                    // Inserir novamente em U
                    individuo.getCromossomos().add(u, valorU);
                    // Valor atual da FO
                    individuo.setFuncaoObjetivo(foAtual);
                    
                }
                
            }
        }
        
    }
    
    //2) remover u e x e inserir u e x após v;
    public void buscaLocalRemoverUXaposV(Individuo individuo) {
        
        Double foAtual = individuo.getFuncaoObjetivo();
        
        for(int u = 0; u < individuo.getCromossomos().size() - 2; u++) {
            int x = u + 1;
            
            for(int v = x + 1; v < individuo.getCromossomos().size(); v++) {
                
                Integer valorU = (Integer) individuo.getCromossomos().get(u);
                Integer valorX = (Integer) individuo.getCromossomos().get(x);
                Integer valorV = (Integer) individuo.getCromossomos().get(v);
                
                // Remover U e X
                individuo.getCromossomos().remove(x);
                individuo.getCromossomos().remove(u);
                
                // Inserir U e X após V
                int posV = individuo.getCromossomos().indexOf(valorV);
                individuo.getCromossomos().add(posV + 1, valorU);
                individuo.getCromossomos().add(posV + 2, valorX);
                
                // Calcular FO
                problema.calcularFuncaoObjetivo(individuo);
                
                if ( individuo.getFuncaoObjetivo() < foAtual ) {
                    // Encerra - first improvement
                    return;
                } else {
                    
                    // Desfaz as inserções.
                    // Remover U e X
                    individuo.getCromossomos().remove(valorU);
                    individuo.getCromossomos().remove(valorX);
                    
                    // Inserir U e X nas posições originais
                    individuo.getCromossomos().add(u, valorU);
                    individuo.getCromossomos().add(x, valorX);
                    individuo.setFuncaoObjetivo(foAtual);
                    
                }
                
                
            }
            
        }
        
    }
    
    //3) remover u e x e inserir x e u após v; (posições invertidas)
    public void buscaLocalRemoverUXInserirXUaposV(Individuo individuo) {
        
        Double foAtual = individuo.getFuncaoObjetivo();
        
        for(int u = 0; u < individuo.getCromossomos().size() - 2; u++) {
            int x = u + 1;
            
            for(int v = x + 1; v < individuo.getCromossomos().size(); v++) {
                
                Integer valorU = (Integer) individuo.getCromossomos().get(u);
                Integer valorX = (Integer) individuo.getCromossomos().get(x);
                Integer valorV = (Integer) individuo.getCromossomos().get(v);
                
                // Remover U e X
                individuo.getCromossomos().remove(x);
                individuo.getCromossomos().remove(u);
                
                // Inserir X e U após V
                int posV = individuo.getCromossomos().indexOf(valorV);
                individuo.getCromossomos().add(posV + 1, valorX);
                individuo.getCromossomos().add(posV + 2, valorU);
                
                // Calcular FO
                problema.calcularFuncaoObjetivo(individuo);
                
                if ( individuo.getFuncaoObjetivo() < foAtual ) {
                    // Encerra - first improvement
                    return;
                } else {
                    
                    // Desfaz as inserções.
                    // Remover U e X
                    individuo.getCromossomos().remove(valorU);
                    individuo.getCromossomos().remove(valorX);
                    
                    // Inserir U e X nas posições originais
                    individuo.getCromossomos().add(u, valorU);
                    individuo.getCromossomos().add(x, valorX);
                    individuo.setFuncaoObjetivo(foAtual);
                    
                }
                
                
            }
            
        }
        
    }

    //4) trocar u e v;
    // Trocar U e V
    // First improvement
    public void buscaLocalSwap(Individuo individuo) {
        
        // Custo atual
        Double foAtual = individuo.getFuncaoObjetivo();
        
        for(int u = 0; u < individuo.getCromossomos().size() - 1; u++) {
            for(int v = u + 1; v < individuo.getCromossomos().size(); v++) {
                // Operar Swap(u, v)
                Collections.swap(individuo.getCromossomos(), u, v);
                // Calcular a diferença 
                problema.calcularFuncaoObjetivo(individuo);
                
                // Se existe melhora
                if ( individuo.getFuncaoObjetivo() < foAtual ) {
                    // Encerrar - first improvement
                    return;
                } else { // Não existe melhora             
                    // Desfazer a troca
                    Collections.swap(individuo.getCromossomos(), u, v);
                    // Retornar o valor da FO
                    individuo.setFuncaoObjetivo(foAtual);
                }
            }
        }
        
    }
        
}
