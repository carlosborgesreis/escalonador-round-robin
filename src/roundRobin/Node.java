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
public class Node {

    private Process data;
    private Node next;

    public Node(Process dado) {
        this.data = dado;
        next = null;
    }

    public Process getData() {
        return data;
    }

    public void setData(Process data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
