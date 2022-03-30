package com.example.cinepolisapp.entidades;

import java.io.Serializable;

public class Alimento implements Serializable {
    private int foodID;
    private String name;
    private int stock;
    private int type;
    private int price;

    public Alimento(int foodID, String name, int stock, int type, int price) {
        this.foodID = foodID;
        this.name = name;
        this.stock = stock;
        this.type = type;
        this.price = price;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
