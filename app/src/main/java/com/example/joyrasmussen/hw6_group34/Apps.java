package com.example.joyrasmussen.hw6_group34;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by joyrasmussen on 2/25/17.
 */

public class Apps {
    String name;
    String imageURL;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    String currency;
    double price;
    boolean isFav;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }


    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return name + "\nPrice : " + currency + " " + formatter.format(price);
    }
}
