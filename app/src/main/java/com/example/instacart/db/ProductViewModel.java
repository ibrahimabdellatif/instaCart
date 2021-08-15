package com.example.instacart.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.instacart.ProductItems;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository repository;
    private LiveData<List<ProductItems>> allProduct;

    public ProductViewModel(@NonNull @org.jetbrains.annotations.NotNull Application application) {
        super(application);
        repository = new ProductRepository(application);
        allProduct = repository.getAllItems();
    }

    public void insert(ProductItems items) {
        repository.insert(items);
    }

    public void update(ProductItems items) {
        repository.update(items);
    }

    public void delete(ProductItems items) {
        repository.delete(items);
    }

    public void deleteAllProduct() {
        repository.deleteAllItems();
    }

    public LiveData<Double> getTotalPrice() {
        return repository.getTotalPrice();
    }

    public LiveData<List<ProductItems>> getAllProduct() {
        return allProduct;
    }
}
