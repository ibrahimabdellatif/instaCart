package com.example.instacart;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instacart.Adapter.ProductAdapter;

import java.util.ArrayList;

public class AllProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProductAdapter adapter;
    private ArrayList<ProductItems> productList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        createProductItems();
        buildRecyclerView();
        setTitle("All Items");


    }

    public void createProductItems() {

        productList.add(new ProductItems(R.drawable.apple, "Organic Apple", 16.56, 0, 0));
        productList.add(new ProductItems(R.drawable.banana, "Organic Banana(each)", 15.00, 0, 0));
        productList.add(new ProductItems(R.drawable.avocado, "Organic Hass Avocado", 23.65, 0, 0));
        productList.add(new ProductItems(R.drawable.strawberrypng, "Juicy Strawberry", 20.65, 0, 0));
        productList.add(new ProductItems(R.drawable.apple, "Organic Apple", 16.56, 0, 0));
        productList.add(new ProductItems(R.drawable.banana, "Organic Banana(each)", 15.00, 0, 0));
        productList.add(new ProductItems(R.drawable.avocado, "Organic Hass Avocado", 23.65, 0, 0));
        productList.add(new ProductItems(R.drawable.strawberrypng, "Juicy Strawberry", 20.65, 0, 0));
        productList.add(new ProductItems(R.drawable.apple, "Organic Apple", 16.56, 0, 0));
        productList.add(new ProductItems(R.drawable.banana, "Organic Banana(each)", 15.00, 0, 0));
        productList.add(new ProductItems(R.drawable.avocado, "Organic Hass Avocado", 23.65, 0, 0));
        productList.add(new ProductItems(R.drawable.strawberrypng, "Juicy Strawberry", 20.65, 0, 0));
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.rv_popular_items);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        adapter = new ProductAdapter(productList);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickLister(new ProductAdapter.OnProductListener() {
            @Override
            public void onProductClick(ProductItems productItems) {
                Toast.makeText(AllProductActivity.this, productItems.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}