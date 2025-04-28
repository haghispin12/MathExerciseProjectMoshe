/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytreemm;

/**
 *
 * @author user
 */
public class BinaryTreeMM {
    
    
    public static void main(String[] args) {
       BinNode<Integer> bt = new BinNode<>(8);
       BinNode<Integer> bt1 = new BinNode<>(1);
       BinNode<Integer> bt2 = new BinNode<>(9);
       BinNode<Integer> bt3 = new BinNode<>(5);
       BinNode<Integer> bt4 = new BinNode<>(7);
       BinNode<Integer> bt5 = new BinNode<>(5);
       BinNode<Integer> bt6 = new BinNode<>(0);
       bt.setLeft(bt1);
       bt.setRight(bt2);
       bt.getRight().setLeft(bt3);
       bt.getRight().setRight(bt4);
       bt.getLeft().setLeft(bt5);
       bt.getRight().getRight().setLeft(bt6);
       
    }
    
    public static boolean isNegative(BinNode<Integer> tr){
        if(tr!=null){
            if(tr.getValue()<0){
                return true;
            }else{
                return isNegative(tr.getLeft()) || isNegative(tr.getRight());
            }
        }
        return false;
    }
    
    public static int countTri(BinNode<Integer> tr, int n){
        if(n!=0 && tr.hasLeft() && tr.hasRight()){
            return countTri(tr.getLeft(), n-1) + countTri(tr.getRight(), n-1);
        } else if(tr.hasLeft() && tr.hasRight()){
            return 1;
        } else {
            return 0;
        }
    }
    public static boolean exist(BinNode<Integer> t, int x){
        if(t.hasLeft() && t.hasRight()){
            if(t.getValue()==x){
                return true;
            }
        }
        return false;
    }
    public static Node<Integer> checl(BinNode<Integer> t1,BinNode<Integer> t2, Node<Integer> list){
        if(!exist(t2, t1.getValue())){
            list.setNext(t1.getValue);
            list = list.getNext();
            
        }
    }
}
