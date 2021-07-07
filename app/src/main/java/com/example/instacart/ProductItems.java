package com.example.instacart;

public class ProductItems {

    private int imageResourceProduct;
    private String nameProduct;
    private String priceProduct;

    private int imageResourceAllCategories;
    private String allCategoriesName;

    public ProductItems(int mImageResourceProduct, String mNameProduct, String mPriceProduct) {
        this.imageResourceProduct = mImageResourceProduct;
        this.nameProduct = mNameProduct;
        this.priceProduct = mPriceProduct;
    }
//    public ProductItems(int mImageResourceAllCategories , String mAllCategoriesName) {
//        this.imageResourceAllCategories = mImageResourceAllCategories;
//        this.allCategoriesName = mAllCategoriesName;
//    }

    public int getImageResourceProduct() {
        return imageResourceProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

//    public int getImageResourceAllCategories() {
//        return imageResourceAllCategories;
//    }
//
//    public String getAllCategoriesName() {
//        return allCategoriesName;
//    }
}
