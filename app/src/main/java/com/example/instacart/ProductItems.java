package com.example.instacart;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class ProductItems {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private int image;
    private String name;
    private double price;
    private int numberOfProduct;

    public ProductItems(int image, String name, double price, int numberOfProduct, int id) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.numberOfProduct = numberOfProduct;
    }

    @Ignore
    public ProductItems(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
}
