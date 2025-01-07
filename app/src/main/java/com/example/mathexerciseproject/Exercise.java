package com.example.mathexerciseproject;

import java.util.Random;

public class Exercise {
    private int num1;
    private int num2;
    private int num3;
    private boolean hasChecked;
    private boolean hasChecked2;
    private Integer bet = new Integer(0);


    /*
    getters
     */

    public boolean isHasChecked2() {
        return hasChecked2;
    }

    public boolean isHasChecked() {return hasChecked;}

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getBet() {return bet;}

    public int getnum3() {return num3;}
    /*
    setters
     */

    public void setHasChecked(boolean hasChecked) {
        this.hasChecked = hasChecked;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    /*
        Generate Numbers for challenge
         */
    public void generateNum1(int min, int max){
        Random r=new Random();
        int sum = max-min;
        int randomNum = r.nextInt(sum)+min;
        num1= randomNum;
    }
    public void generateNum2(int min, int max) {
        Random r = new Random();
        int sum = max - min;
        int randomNum = r.nextInt(sum) + min;
        num2 = randomNum;
    }



    /*
    Check Answer
     */
    public boolean checkAnswers(String answer) {
        num3 = num1*num2;
        if (Integer.valueOf(answer) == num3) {
            return true;
        } else{
            return false;
        }
    }



}
