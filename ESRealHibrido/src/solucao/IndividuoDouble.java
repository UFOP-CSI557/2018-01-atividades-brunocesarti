/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solucao;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author fernando
 */
public class IndividuoDouble implements Individuo<Double> {

    // Genótipo+Fenotipo
    private ArrayList<Double> cromossomos;
    // Custo da função objetivo
    Double funcaoObjetivo;

    // Valor mínimo
    Double minimo;
    // Valor máximo
    Double maximo;

    // Número de variáveis
    Integer nVar;

    public IndividuoDouble(Double minimo, Double maximo, Integer nVar) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVar = nVar;
        this.cromossomos = new ArrayList<>();
    }

    @Override
    public ArrayList<Double> getCromossomos() {
        return cromossomos;
    }

    @Override
    public void setCromossomos(ArrayList<Double> cromossomos) {
        this.cromossomos = cromossomos;
    }

    @Override
    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    @Override
    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public Integer getnVar() {
        return nVar;
    }

    public void setnVar(Integer nVar) {
        this.nVar = nVar;
    }

    // Gerar o genótipo
    @Override
    public void criar() {

        this.cromossomos = new ArrayList<>();

        Random rnd = new Random();
        Double valor;

        for (int i = 0; i < this.getnVar(); i++) {
            valor = this.minimo
                    + (this.maximo - this.minimo)
                    * rnd.nextDouble();
            this.cromossomos.add(valor);
        }
    }

    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }

    @Override
    public Individuo<Double> clone() {
        Individuo individuo = null;
        individuo
                = new IndividuoDouble(this.getMinimo(),
                        this.getMaximo(),
                        this.getnVar());
        individuo.setCromossomos(new ArrayList<>(this.getCromossomos()));
        individuo.setFuncaoObjetivo(this.getFuncaoObjetivo());
        return individuo;
    }

    @Override
    public String toString() {
        return "Individuo{" + "cromossomos=" + cromossomos + ", funcaoObjetivo=" + funcaoObjetivo + '}';
    }
    
}
