/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundRobin;

/**
 *
 * @author carlos.reis
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    @SuppressWarnings("empty-statement")
    public static void readProcess(String path, Queue processes) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));

        String line = "";
        int emptyLine = 0;
        try {
            while (emptyLine < 2) {

                Process process = new Process();
                Node current = new Node(process);
                if (line != null) {

                    line = buffRead.readLine();
                    current.getData().setName(line);

                    line = buffRead.readLine();
                    current.getData().setBurst(Integer.parseInt(line));

                    line = buffRead.readLine();
                    current.getData().setSubmission(Integer.parseInt(line));

                    line = buffRead.readLine();
                    current.getData().setIO(line);

                    if (line.equalsIgnoreCase("SIM")) {

                        line = buffRead.readLine();
                        CharSequence a = Integer.toString(current.getData().burst);
                        if(line.contains(a) || line.contains("1")){
                            System.out.println("Última operação do processo deve ser na CPU, não em I/O, favor mudar o processo " + current.getData().name);
                            throw new IllegalArgumentException();
                        }
                        current.getData().setopIO(line.trim());

                    }
                    processes.enqueue(process);
                } else {
                    emptyLine++;
                    break;
                }

                line = buffRead.readLine();

            }

            buffRead.close();

        } catch (IOException e) {
            System.out.println("Erro de leitura de arquivo : " + e);;
        } catch (IllegalArgumentException a){
            System.out.println(a);
        }

    }
}
