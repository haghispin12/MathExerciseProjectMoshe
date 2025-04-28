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


/**
 * Created by asafamir on 08/02/16.
 */
public class Stack<T>
{
    private Node<T> first;

    public Stack()
    {
        this.first=null;
    }
    public boolean isEmpty()
    {
        return (this.first==null);
    }
    public void push(T x)
    {
        // insert in head
        this.first=new Node<T> (x,this.first);
    }
    public T pop()
    {
        T x=this.first.getValue();
        this.first=this.first.getNext();
        return  x;
    }
    public T top()
    {
        return this.first.getValue();
    }
    public String toString()
    {
        String str="[";
        Node<T> pos=this.first;
        while (pos!=null)
        {
            str+=pos.getValue();
            pos=pos.getNext();
            if (pos!=null)
                str+=",";
        }

        return str+"]";
    }
    public int size()
    {
        int counter=0;
        Node<T> temp=this.first;
        while(temp!=null)
        {
            counter++;
            temp=temp.getNext();
        }
        return counter;
    }

}