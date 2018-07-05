package pqa;

import java.util.LinkedList;

public class Movimento {

    Individuo ind;
    LinkedList<Integer> genes;
    int posicao;
    int u;
    int v;
    int x;
    int y;
    int tmpu;
    int tmpv;
    int tmpx;
    int tmpy;

    public Movimento(Individuo ind, int u, int v) {
        this.ind = ind;
        this.genes = ind.getGenes();
        this.u = u;
        this.v = v;
        this.x = u + 1;
        this.y = v + 1;
        this.tmpu = ind.getGenes().get(u);
        this.tmpv = ind.getGenes().get(v);
        this.tmpx = ind.getGenes().get(x);
        this.tmpy = ind.getGenes().get(y);
    }

    public Individuo movimento1() {
        genes.remove(u);
        posicao = genes.indexOf(tmpv);
        genes.add(posicao + 1, tmpu);
        ind.setGenes(genes);
        return ind;
    }

    public Individuo movimento2() {

        genes.remove(u);
        genes.remove(u);
        posicao = genes.indexOf(tmpv);
        genes.add(posicao + 1, tmpx);
        genes.add(posicao + 1, tmpu);
        ind.setGenes(genes);
        return ind;
    }

    public Individuo movimento3() {
        genes.remove(u);
        genes.remove(u);
        posicao = genes.indexOf(tmpv);
        genes.add(posicao + 1, tmpu);
        genes.add(posicao + 1, tmpx);
        ind.setGenes(genes);
        return ind;
    }

    public Individuo movimento4() {
        genes.set(u, tmpv);
        genes.set(v, tmpu);
        ind.setGenes(genes);
        return ind;
    }

    public Individuo movimento5() {
        genes.set(u, tmpv);
        genes.set(v, tmpu);
        genes.remove(x);
        posicao = genes.indexOf(tmpu);
        genes.add(posicao, tmpx);
        ind.setGenes(genes);
        return ind;
    }

    public Individuo movimento6() {
        genes.set(u, tmpy);
        genes.set(x, tmpv);
        genes.set(v, tmpx);
        genes.set(y, tmpu);
        ind.setGenes(genes);
        return ind;
    }

    public Individuo movimento7() {

        int size = genes.size();

        LinkedList<Integer> newGenes = new LinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            newGenes.add(0);
        }
        // 1. take route[0] to route[i-1] and add them in order to new_route
        for (int c = 0; c <= u - 1; ++c) {
            newGenes.set(c, genes.get(c));
        }

        // 2. take route[i] to route[k] and add them in reverse order to
        // new_route
        int dec = 0;
        for (int c = u; c <= v; ++c) {
            newGenes.set(c, genes.get(v - dec));
            dec++;
        }

        // 3. take route[k+1] to end and add them in order to new_route
        for (int c = v + 1; c < size; ++c) {
            newGenes.set(c, genes.get(c));
        }

        ind.setGenes(newGenes);
        return ind;
    }
}
