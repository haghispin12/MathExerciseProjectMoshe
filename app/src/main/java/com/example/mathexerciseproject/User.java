package com.example.mathexerciseproject;

public class User {
    private String UserName;
    private int Score;
    private int Rate;


    /*
        getters
         */
    public String getUserName() {
        return UserName;
    }

    public int getScore() {
        return Score;
    }

    public int getRate() {
        return Rate;
    }
    /*
    setters
     */

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setRate(int rate) {
        this.Rate = rate;
    }

}
