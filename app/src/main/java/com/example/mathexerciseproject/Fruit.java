package com.example.mathexerciseproject;

public class Fruit {

    private String name;
    private int image;

    /*
    constructors
     */
    public Fruit(String name, int image) {
        this.name = name;
        this.image = image;
    }

    /*
    getters and setters
     */
    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

}
