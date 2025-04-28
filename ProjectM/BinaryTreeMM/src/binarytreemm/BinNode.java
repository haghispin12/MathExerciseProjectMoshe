/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreemm;

import javafx.scene.Node;

/**
 *
 * @author user
 */
public class BinNode<T> {
    private T value;
    private BinNode leftChild;
    private BinNode rightChild;
    
    public BinNode(T value){
        this.value=value;
        leftChild = null;
        rightChild = null;
    }

    public BinNode(T value, BinNode lefNode, BinNode rightChild) {
        this.value=value;
        this.leftChild=leftChild;
        this.rightChild=rightChild;
    }

    public T getValue() {
        return value;
    }

    
    public BinNode getLeft() {
        return leftChild;
    }

    public BinNode getRight() {
        return rightChild;
    }

    public boolean hasLeft(){
        if(leftChild!=null)
            return true;
        return false;
    }
     public boolean hasRight(){
        if(rightChild!=null)
            return true;
        return false;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BinNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRight(BinNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
}
