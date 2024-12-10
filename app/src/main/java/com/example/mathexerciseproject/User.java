package com.example.mathexerciseproject;

import android.net.Uri;

public class User {
    private String UserName;
    private int Score;
    private int Rate;
    private Long id;
    Uri uri;


    /*
    getters
     */
    public Long getId() {return id;}

    public Uri getUri() {return uri;}

    public String getUserName() {return UserName;}

    public int getScore() {
        return Score;
    }

    public int getRate() {
        return Rate;
    }
    /*
    setters
     */
    public void setUri(Uri uri) {this.uri = uri;}

    public void setId(Long id) {this.id = id; }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setRate(int rate) {this.Rate = rate;}
}
