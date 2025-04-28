/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poject.mm;

/**
 *
 * @author Haggay
 */
public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T info) {
        this.value = info;
        this.next = null;        
    }

    public Node(T info, Node<T> next) {
        this.value = info;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return  value + "->" + next ;
    }
    
    
    
    
    
}