package pqa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import static pqa.Main.solucao;

public class CsvFile {
//CsvFile.escreverResultado(nomeInstancia, numDescendentes, tamanhoPop, parada, pMutacao, pCrossover, geracoes,melhor.genesToSrting(), melhor.getFitness(), solucao, gap, segundos);
public static void escreverResultado(String arquivo, int numDescendentes, int tamanhoPop, long tempoParada, double taxaMutacao, double taxaCrossover, int geracoes, String genes, int resultado, int solucao, double gap, double segundos) {
 
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        FileWriter fileWriter = null;
        File file = new File("teste.csv");
        StringBuilder sb = new StringBuilder();

        try {
            if (file.exists() && !file.isDirectory()) {
                fileWriter = new FileWriter(file, true);
                sb.append(NEW_LINE_SEPARATOR);
                sb.append(arquivo);
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(numDescendentes));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(tamanhoPop));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(tempoParada));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(taxaMutacao));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(taxaCrossover));
                sb.append(COMMA_DELIMITER);
                sb.append(geracoes);
                sb.append(COMMA_DELIMITER);
                sb.append(genes);
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(resultado));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(solucao));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(gap));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(segundos));
                fileWriter.append(sb.toString());

            } else {
                fileWriter = new FileWriter(file);
                String FILE_HEADER = "ARQUIVO,NUMERO DE DESENDENTES,TAMANHO DA POPULACAO,CRITERIO DE PARADA,TAXA DE MUTACAO,TAXA DE CROSSOVER, NUMERO DE GERACOES,GENES,RESULTADO,SOLUCAO,GAP,TEMPO DE EXECUCAO";
                sb.append(FILE_HEADER.toString());
                sb.append(NEW_LINE_SEPARATOR);
                sb.append(arquivo);
                sb.append(COMMA_DELIMITER);
sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(numDescendentes));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(tamanhoPop));
sb.append(COMMA_DELIMITER);

                sb.append(String.valueOf(tempoParada));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(taxaMutacao));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(taxaCrossover));
                sb.append(COMMA_DELIMITER);
                sb.append(geracoes);
                sb.append(COMMA_DELIMITER);
                sb.append(genes);
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(resultado));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(solucao));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(gap));
                sb.append(COMMA_DELIMITER);
                sb.append(String.valueOf(segundos));
                fileWriter.append(sb.toString());
            }

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

}
