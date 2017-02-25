package com.example.joyrasmussen.hw6_group34;


import java.io.Serializable;

public class App implements Serializable{

    String name, price, currency, imageURL;
    Boolean isFavorite;

    public App(String name, String price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
        this.isFavorite = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return  name + "\nPrice: " + currency + " " + price ;
    }
}
