package pqa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AGQPAMain {

    static int tamanhoInstancia;
    static int solucao;
    static ArrayList<ArrayList<Integer>> fluxo = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> distancia = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) {

        AGQPA ag = null;
        for (int i = 0; i < 2; i++) {

//              DEFINICAO DE´PARAMETROS
            String nomeInstancia = "C:/Users/Bruno/Documents/NetBeansProjects/CSI557-Computacao-Evolucionaria-master/Codes/TP_Final/esc16a.dat";
            String solucaoInstancia = "C:/Users/Bruno/Documents/NetBeansProjects/CSI557-Computacao-Evolucionaria-master/Codes/TP_Final/esc16a.sln";
            int tamanhoPop = 30;    // Default -> 30  // TAMANHO DA POPULACAO 
            double pMutacao = 0.3;  // Default -> 0.3   // TAXA DE MUTACAO
            double pCrossover = 0.2;    // Default -> 0.2    // TAXA DE CROSSOVER
            int geracoes = 100;     // Default ->100    // NUMERO DE GERACOES - ESTA OPÇÃO NAO ESTA SENDO UTILIZADA NO MOMENTO POIS O CRITERIO DE PARADA ATUAL E BASEADO NO TEMPO ESTIPULADO, CONTUDO ESTA DISPONIVEL PARA CASO QUEIRA USA-LA 
            long parada = 20; // Default -> O VALOR 10 equivale a aproximadamente 1 min  // CRITERIO DE PARADA EM FUNCAO DO TEMPO
            int numDescendentes = 10; // Default -> 10  // NUMERO DE DESCENDENTES

            lerArquivo(nomeInstancia, solucaoInstancia);
            long start = System.nanoTime();

//                    System.out.println("Fluxo: " + fluxo + " Distancia: " + distancia + " Tamanho Instancia: " + tamanhoInstancia);
            ag = new AGQPA(fluxo, distancia, tamanhoInstancia, 
                    numDescendentes, tamanhoPop, parada, pMutacao, pCrossover,geracoes);

            Individuo melhor = ag.gerarSolucao();
            System.out.println("Melhor individuo: " + melhor.toString());

            long elapsedTime = System.nanoTime() - start;
            double segundos = (double) elapsedTime / 1000000000.0;

            double gap = (double) (melhor.getFitness() - solucao) / solucao;
            System.out.println("Gap: " + gap);

            nomeInstancia = "esc16a.dat";
//            ARQUIVO,NUMERO DE DESENDENTES,TAMANHO DA POPULACAO,CRITERIO DE PARADA,TAXA DE MUTACAO,TAXA DE CROSSOVER, NUMERO DE GERACOES,GENES,RESULTADO,SOLUCAO,GAP,TEMPO DE EXECUCAO
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
