package pqa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Individuo implements Comparable<Individuo>, Serializable {

    // FBO: lista encadeada?
    private LinkedList<Integer> genes;
    private Integer fitness;

    public Individuo() {

    }

    public LinkedList<Integer> getGenes() {
        return genes;
    }

    public void setGenes(LinkedList<Integer> genes) {
        this.genes = genes;
    }

    public Integer getFitness() {
        return fitness;
    }

    public void setFitness(int custo) {
        this.fitness = custo;
    }

    @Override
    public String toString() {
        return "[" + genes + " fitness=" + fitness + "]";
    }

    public String genesToSrting() {
        String g = "[ ";
        for (int i = 0; i < genes.size(); i++) {
            g = g + genes.get(i) + " ";
        }
        g = g + "]";
        return g;
    }

    @Override
    public int compareTo(Individuo individuo) {
        return fitness.compareTo(individuo.getFitness());
    }

    @Override
    public int hashCode() {
        return this.genes.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        boolean isEqual = false;

        Individuo individuo = (Individuo) obj;
        int result1 = this.genes.toString().hashCode();
        int result2 = individuo.genes.toString().hashCode();

        if (result1 == result2) {
            isEqual = true;
        }

        return isEqual;
    }

    public static Individuo deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Individuo) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // FBO: clone?

}
