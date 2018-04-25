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
public class Process {

    String name;
    int burst;
    int submission;
    String IO;
    String opIO;
    
    //Atributo usado para ajudar na implementação do IO
    int reverseBurst = 0;
    int totalBurst = burst;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getSubmission() {
        return submission;
    }

    public void setSubmission(int submission) {
        this.submission = submission;
    }

    public String getIO() {
        return IO;
    }

    public void setIO(String IO) {
        this.IO = IO;
    }

    public String getOpIO() {
        return opIO;
    }

    public void setopIO(String opIO) {
        this.opIO = opIO;
    }

    public int[] converteIO(String IO) {
        int a = 0;
        if (IO != null && IO.length() == 1) {

            int vet[] = new int[1];

            vet[a] = Integer.parseInt(IO);
            a++;
            return vet;

        } else if (IO != null && IO.length() > 1) {
            int vet[] = new int[((IO.length()+1) / 2)];
            for (int i = 0; i < IO.length(); i++) {
                if (i % 2 == 0) {
                    vet[a] = Character.getNumericValue(IO.charAt(i));
                    a++;
                }
            }
            return vet;
        } else {
            return new int[0];
        }
    }

}
