package com.example.instacart;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class ProductItems {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private int imageResourceProduct;
    private String nameProduct;
    private String priceProduct;
    @Ignore
    private int imageResourceAllCategories;
    @Ignore
    private String allCategoriesName;

    public ProductItems(int imageResourceProduct, String nameProduct, String priceProduct) {
        this.imageResourceProduct = imageResourceProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    @Ignore
    public ProductItems(int mImageResourceAllCategories, String mAllCategoriesName) {
        this.imageResourceAllCategories = mImageResourceAllCategories;
        this.allCategoriesName = mAllCategoriesName;
    }

    public int getImageResourceProduct() {
        return imageResourceProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public int getImageResourceAllCategory() {
        return imageResourceAllCategories;
    }

    public String getAllCategoryName() {
        return allCategoriesName;
    }
}
