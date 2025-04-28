/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poject.mm;

import static java.time.Clock.system;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class PojectMM {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.newDeck();
        deck.printCard();
         
        
        
    }
    public static void printX(int num){
        if(num>0){
            for(int i =0; i<num; i++){
                System.out.print("*");
            }
            System.out.println("");
            printX(num-1);
            for(int i =0; i<num; i++){
                System.out.print("*");
            }
            System.out.println("");
        }   
    }
    public static int getMax(int[] a, int n){
        int max = a[n];
        if(n==0){
            return 0;
        }else{
            if(max<getMax(a, a[n-1])){
                max=a[n-1];
            }
        }
        return max;
    }

    public static Stack<Integer> fill(Stack<Integer> s1){
        Scanner User = new Scanner(System.in);
        int x= User.nextInt();
            while(x!=-1){
                s1.push(x);
                x= User.nextInt();
            }
        return s1;
    }
    public static void Print(Stack<Integer> s1){
        Stack<Integer> s2 = new Stack<>();
        while(!s1.isEmpty()){
            s2.push(s1.pop());
            System.out.println(s2.top().toString());
        }
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
    }
    public static int returnMax(Stack<Integer> s1){
        Stack<Integer> s2 = new Stack<>();
        int max = s1.top();
        while(!s1.isEmpty()){
            if(max<s1.top()){
                max=s1.top();
        }
        s2.push(s1.pop());
        }
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return max;
    }
    public static void removeNum(Stack<Integer> s1, int x){
        Stack<Integer> s2 = new Stack<>();
        while(!s1.isEmpty()){
            if(s1.top()==x){
                s1.pop();
            }else{s2.push(s1.pop());}
        }
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
    }

    public static Stack<Integer> setup(Stack<Integer> s1){
        Stack<Integer> s2 = new Stack<>();
        int x=0;
        while(!s1.isEmpty()){
            x = returnMax(s1);
            s2.push(x);
            removeNum(s1, x);
        }
        
        while(!s2.isEmpty()){s1.push(s2.pop());}
        System.out.println(s1.toString());
        return s1;
    }

}
