package com.example.mathexerciseproject.FishingProject;

import java.util.ArrayList;

public class User {
    private String userName;
    private int balance;
    private long id;
    private ArrayList<Boolean> Inventory = new ArrayList<>();
    private int bucketSize=3;
    private int fishAmount;

    /*
    Constructor
     */
    public User(String userName, int balance, long id, int bucketSize, int fishAmount){
        this.userName = userName;
        this.balance = balance;
        this.id = id;
        this.bucketSize = bucketSize;
        this.fishAmount = fishAmount;
    }

    /*
    Getters
     */
    public int getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }

    public int getBucketSize() {
        return bucketSize;
    }

    public int getFishAmount() {
        return fishAmount;
    }

    public String getUserName() {
        return userName;
    }

    /*
    Setters
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBucketSize(int bucketSize) {
        this.bucketSize = bucketSize;
    }

    public void setFishAmount(int fishAmount) {
        this.fishAmount = fishAmount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
