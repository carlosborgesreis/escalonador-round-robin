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
public class Queue {

    private Node first, last;


    public Queue() {
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return (first == null);
    }

    public Process peek() {
        if (!isEmpty()) {
            Node atual = first;
            return atual.getData();
        } else {
            return null;
        }
    }
    
    public int size(){
        int tam = 0;
        
        Node current = first;
        
        while(current != null){
            tam++;
            current = current.getNext();
        }
        
        return tam;
    }

    public String allList() {
        String saida = "";
        if (!isEmpty()) {
            Node current = first; 
            while (current != null){
                if (current.getData() != null && current.getData() != peek() && current.getData().burst != 0) {
                    saida += current.getData().name + "(" + current.getData().burst + ") ";
                }
                current = current.getNext();
            }
            if (saida.equals("")) {
                return "Não há processos na fila";
            } else {
                return saida;
            }
        }

        return "Não há processos na fila";
    }

    
    public void enqueue(Process dado){
        Node newNode = new Node(dado);

        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }
    
    public Process[] asVector(){
        Process vect[] = new Process[size()];
        Node atual = first;
        for(int i = 0; i < size(); i++){
            vect[i] = atual.getData();
            atual = atual.getNext();
        }
        return vect;
    }

    public boolean find(Process p) {
        Node atual = first;
        while (atual != null){
                if (atual.getData() == p) {
                    return true;
                }
                atual = atual.getNext();
            }
        return false;
    }

 
    public Process dequeue(){
        if (isEmpty()) 
            return null;

        Node tmp = first;
        first = first.getNext();
        if (first == null)
            last = null;
        
        return tmp.getData();
    }
}
