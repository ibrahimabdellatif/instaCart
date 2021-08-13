package com.example.instacart.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.instacart.ProductItems;
import com.example.instacart.R;

@Database(entities = {ProductItems.class}, version = 1)
public abstract class ItemDataBase extends RoomDatabase {

    private static ItemDataBase instance;

    //this method to access to out DAO
    public abstract ItemDao itemDao();

    public static synchronized ItemDataBase getInstance(Context context) {
        //use room builder
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDataBase.class, "item_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallBack).build();
        }
        return instance;
    }

    private static Callback roomCallBack = new Callback() {
        /**
         * onCreate method it used for first time to initiate db for first one.
         * onOpen method it used for every time when you used db.
         */
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private ItemDao itemDao;

        private PopulateDbAsyncTask(ItemDataBase db) {
            itemDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insert(new ProductItems(R.drawable.apple, "product name1", "product price1"));
            itemDao.insert(new ProductItems(R.drawable.avocado, "product name2", "product price2"));
            itemDao.insert(new ProductItems(R.drawable.banana, "product name3", "product price3"));
            return null;
        }
    }

}
