/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poject.mm;

/**
 *
 * @author user
 */
public class Deck {
    private Stack<Card> cards;
    private int totalCards = 52;
    private int[] shapes;

    public Deck() {
        cards = new Stack<>();
    }
    
    public void emptyDeck(){
        while(!cards.isEmpty()){
            cards.pop();
        }
    }
    
    public void newDeck(){
        int currentShape=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<13; j++){
                Card c = new Card(currentShape, j);
                cards.push(c);
            }
            currentShape=i;
        }
    }
    public void printDeck(){
        for(int i=0; i<totalCards; i++){
            Stack<Card> temp = new Stack<>();
            System.out.println("card 1 = the "+ cards.top().getNumber() +"of "+ cards.top().getShape());
            temp.push(cards.pop());
            
        }
    }
    public void printCard(){
        System.out.println("card 1 = "+ cards.top().toString());
    }
    
}
