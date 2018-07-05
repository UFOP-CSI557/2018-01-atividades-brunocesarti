package pqa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int tamanhoInstancia;
    static int solucao;
    static ArrayList<ArrayList<Integer>> fluxo = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> distancia = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) {

        AGQpa ag = null;
        for (int i = 0; i < 1; i++) {

//              DEFINICAO DE´PARAMETROS
            String nomeInstancia = "C:/Users/Bruno/Documents/NetBeansProjects/CSI557-Computacao-Evolucionaria-master/Codes/TP_Final/esc16a.dat";
            String solucaoInstancia = "C:/Users/Bruno/Documents/NetBeansProjects/CSI557-Computacao-Evolucionaria-master/Codes/TP_Final/esc16a.sln";
            
            int tamanhoPop = tamanhoInstancia;
            double pMutacao = 0.4;
            double pCrossover = 0.5;
            int geracoes = 100;
            long parada = 10;
            int numDescendentes = tamanhoInstancia;
            // tamanhoInstancia definido global;

            lerArquivo(nomeInstancia, solucaoInstancia);
            long start = System.nanoTime();

//                    System.out.println("Fluxo: " + fluxo + " Distancia: " + distancia + " Tamanho Instancia: " + tamanhoInstancia);
            ag = new AGQpa(fluxo, distancia, tamanhoInstancia, numDescendentes, tamanhoPop, parada, pMutacao, pCrossover,geracoes);

            Individuo melhor = ag.gerarSolucao();
            System.out.println("Melhor individuo: " + melhor.toString());

            long elapsedTime = System.nanoTime() - start;
            double segundos = (double) elapsedTime / 1000000000.0;

            double gap = (double) (melhor.getFitness() - solucao) / solucao;
            System.out.println("Gap: " + gap);

            nomeInstancia = "esc16a.dat";
            CsvFile.escreverResultado(nomeInstancia, numDescendentes, tamanhoPop, parada, pMutacao, pCrossover, geracoes,melhor.genesToSrting(), melhor.getFitness(), solucao, gap, segundos);
        }
    }

    public static void lerArquivo(String arquivo, String sln) {

        fluxo.clear();
        distancia.clear();
        boolean primeiro = true;

        try {
            Scanner scanner = new Scanner(new FileReader(arquivo));
            while (scanner.hasNext()) {
                if (primeiro) {
                    tamanhoInstancia = scanner.nextInt();
                    primeiro = false;
                } else {

                    for (int i = 0; i < tamanhoInstancia; i++) {
                        fluxo.add(i, new ArrayList<Integer>());
                        distancia.add(i, new ArrayList<Integer>());
                    }

                    for (int i = 0; i < tamanhoInstancia; i++) {
                        for (int j = 0; j < tamanhoInstancia; j++) {
                            fluxo.get(i).add(j, scanner.nextInt());
                        }
                    }

                    for (int i = 0; i < tamanhoInstancia; i++) {
                        for (int j = 0; j < tamanhoInstancia; j++) {
                            distancia.get(i).add(j, scanner.nextInt());
                        }
                    }
                }
            }
            scanner.close();

            scanner = new Scanner(new FileReader(sln));
            int i = 0;
            while (scanner.hasNext() && i < 2) {
                solucao = scanner.nextInt();
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
