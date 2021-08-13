package com.example.instacart.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.instacart.ProductItems;

import java.util.List;

public class ProductRepository {
    private ItemDao itemDao;
    private LiveData<List<ProductItems>> allProduct;

    public ProductRepository(Application application) {
        ItemDataBase dataBase = ItemDataBase.getInstance(application);
        itemDao = dataBase.itemDao();
        allProduct = itemDao.getAllProductInCart();

    }

    public void insert(ProductItems items) {
        new InsertProductAsyncTask(itemDao).execute(items);
    }

    public void update(ProductItems items) {
        new UpdateProductAsyncTask(itemDao).execute(items);
    }

    public void delete(ProductItems items) {
        new DeleteProductAsyncTask(itemDao).execute(items);
    }

    public void deleteAllItems() {
        new DeleteAllProductAsyncTask(itemDao).execute();
    }
    public LiveData<List<ProductItems>> getAllNotes() {
        return allProduct;
    }

    // it takes three parameters
    public static class InsertProductAsyncTask extends AsyncTask<ProductItems, Void, Void> {
        private ItemDao itemDao;

        private InsertProductAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ProductItems... product) {
            itemDao.insert(product[0]);
            return null;
        }
    }

    public static class UpdateProductAsyncTask extends AsyncTask<ProductItems, Void, Void> {
        private ItemDao itemDao;

        private UpdateProductAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ProductItems... product) {
            itemDao.update(product[0]);
            return null;
        }
    }

    public static class DeleteProductAsyncTask extends AsyncTask<ProductItems, Void, Void> {
        private ItemDao itemDao;

        private DeleteProductAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(ProductItems... product) {
            itemDao.delete(product[0]);
            return null;
        }
    }

    public static class DeleteAllProductAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private DeleteAllProductAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.deleteAllItems();
            return null;
        }
    }

}
