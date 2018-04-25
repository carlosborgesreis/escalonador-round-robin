/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundRobin;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


/**
 *
 * @author carlos.reis
 */
public class RoundRobinApp {

    /**
     * @throws java.io.IOException
     */
    public static Queue filaRR = new Queue();
    public static Queue filaProcessos = new Queue();
    
    public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO code application logic here

        URL url = RoundRobinApp.class.getResource("processo.txt");

        String path = url.getPath();
         
         
         

        
        FileManager.readProcess(path, filaProcessos);
        
        RoundRobin roundRobin = new RoundRobin(filaRR, filaProcessos);
        
        System.out.println(roundRobin.run());
}}
