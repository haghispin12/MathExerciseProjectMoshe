package com.example.mathexerciseproject;

import java.util.Random;

public class Exercise {
    private int num1;
    private int num2;


    /*
    getters
     */
    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }
    /*
    setters
     */

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
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
        if (Integer.valueOf(answer) == (num1 * num2)) {
            return true;
        } else{
            return false;
        }
    }

}
