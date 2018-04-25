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
public class RoundRobin {

    Queue roundRobinFila;
    Queue processes;

    public RoundRobin(Queue roundRobin, Queue processos) {
        this.roundRobinFila = roundRobin;
        this.processes = processos;
    }

    public String run() {
        String saida = "";

        saida += "*********************************\n";
        saida += "**** Escalonador Round-Robin ****\n";
        saida += "*********************************\n\n";

        int quantum = 4;
        int cont = 1;
        int totalSize = totalSize();

        for (int i = 0; i <= totalSize; i++) {
            boolean breakOuterFor = false;
            String description = "";
            boolean isLastCPU = (i == totalSize);

            for (Process process : processes.asVector()) {
                if (process.submission == i) {
                    roundRobinFila.enqueue(process);
                    description += " chegada de processo " + process.name;
                }
            }

            Process process = roundRobinFila.peek();

            if (process != null) {
                boolean onQuantum = cont >= quantum;
                boolean isEnd = process.burst == 0;
                int[] vetorIO = process.converteIO(process.opIO);
                boolean hasIO = vetorIO.length != 0;

                if (hasIO) {
                    for (int j : vetorIO) {
                        boolean isIO = process.reverseBurst == j;

                        if (isIO) {
                            description += " operação de I/O " + process.name;
                            process = changePeek(roundRobinFila, process);
                            cont = 1;

                            if (!isEnd) {
                                if (!onQuantum) {
                                    saida += execProcessNotQuantum(description, i, process, roundRobinFila);
                                    breakOuterFor = true;
                                    break;
                                } else if (onQuantum) {
                                    saida += execProcessOnQuantum(description, i, process, roundRobinFila);
                                    cont = 1;

                                    breakOuterFor = true;
                                    break;
                                }
                            } else if (isEnd) {
                                saida += execProcessEnd(description, i, process, roundRobinFila, breakOuterFor);
                                cont = 1;
                                break;
                            }
                        }
                    }
                }

                if (breakOuterFor) {
                    continue;
                }

                if (!isEnd) {
                    if (!onQuantum) {
                        saida += execProcessNotQuantum(description, i, process, roundRobinFila);
                        cont++;
                    } else if (onQuantum) {
                        saida += execProcessOnQuantum(description, i, process, roundRobinFila);
                        cont = 1;
                    }

                } else if (isEnd) {
                    saida += execProcessEnd(description, i, process, roundRobinFila, breakOuterFor);
                    cont = 1;
                }
            }

            if (isLastCPU) {
                saida += "Tempo: " + i + " fim de processo " + process.name + "\n";
                saida += "Fila: " + roundRobinFila.allList() + "\n\n";
            }
        }

        saida += "*************************************\n";
        saida += "* Fim do simulador de escalonamento *\n";
        saida += "*************************************\n\n";

        return saida;
    }

    private String execProcessNotQuantum(String description, int i, Process process, Queue roundRobinFila) {
        String saida = "";
        saida += "Tempo: " + i + description + "\n";
        saida += "Fila: " + roundRobinFila.allList() + "\n";
        saida += "CPU: " + process.name + "(" + process.burst + ")\n\n";
        process.burst--;
        process.reverseBurst++;

        return saida;
    }

    private String execProcessOnQuantum(String description, int i, Process process, Queue roundRobinFila) {
        String saida = "";

        description = " fim de quantum " + process.name;
        process = changePeek(roundRobinFila, process);
        saida += "Tempo: " + i + description + "\n";
        saida += "Fila: " + roundRobinFila.allList() + "\n";
        saida += "CPU: " + process.name + "(" + process.burst + ")\n\n";
        process.burst--;
        process.reverseBurst++;

        return saida;
    }

    private String execProcessEnd(String description, int i, Process process, Queue roundRobinFila, boolean breakOuterFor) {
        String saida = "";
        description = " fim de processo " + process.name;
        roundRobinFila.dequeue();
        process = roundRobinFila.peek();
        if (process != null) {
            saida += "Tempo: " + i + description + "\n";
            saida += "Fila: " + roundRobinFila.allList() + "\n";
            saida += "CPU: " + process.name + "(" + process.burst + ")\n\n";
            process.burst--;
            process.reverseBurst++;
            breakOuterFor = true;
        }

        return saida;
    }
    
    private Process changePeek(Queue roundRobinFila, Process process) {
        roundRobinFila.dequeue();
        roundRobinFila.enqueue(process);
        process = roundRobinFila.peek();
        return process;
    }
    
    private int totalSize() {
        int totalSize = 0;

        for (Process process : processes.asVector()) {
            totalSize += process.burst;
        }

        return totalSize;
    }
}
