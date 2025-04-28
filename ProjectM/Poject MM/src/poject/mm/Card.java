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
public class Card {
    private int colorNum; // 1=black, 0=white
    private int shapeNum; // 0=clubs, 1=cloves, 2=hearts, 3=diamonds
    private int numberNum;
    private String number;
    private String color;
    private String shape;

    public Card(int shape, int number){
        if(shape==0 || shape==1){
            this.colorNum=1;
            this.color = "black";
            if(shape==0)
                this.shape="clubs";
            else
                this.shape="cloves";
        }else{
            this.colorNum=0;
            this.color = "white";
            if(shape==2)
                this.shape="hearts";
            else
                this.shape="diamonds";
        }
        this.numberNum = number;
        if(number==0)
            this.number="Ace";
        else if(number==1)
            this.number="Two";
        else if(number==2)
            this.number="Two";
        else if(number==3)
            this.number="Two";
        else if(number==4)
            this.number="Two";
        else if(number==5)
            this.number="Two";
        else if(number==6)
            this.number="Two";
        else if(number==7)
            this.number="Two";
        else if(number==8)
            this.number="Two";
        else if(number==9)
            this.number="Two";
        else if(number==10)
            this.number="Two";
        else if(number==11)
            this.number="Two";
        else if(number==12)
            this.number="Two";
    }

    public int getColor() {
        return colorNum;
    }

    public int getShape() {
        return shapeNum;
    }

    public int getNumber() {
        return numberNum;
    }

    public void setColor(int color) {
        this.colorNum = color;
    }

    public void setShape(int shape) {
        this.shapeNum = shape;
    }

    public void setNumber(int number) {
        this.numberNum = number;
    }
    public void printCard(){
        System.out.println("the "+numberNum);
    }
}
