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
    public int generateNumbers(int min, int max){
        Random r=new Random();
        int sum = max-min; // otherwise if received (10, 20) max will be 30
        int randomNum = r.nextInt(sum)+min;
        return randomNum;
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
