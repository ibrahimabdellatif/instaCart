package com.example.instacart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.instacart.MainActivity.EXTRA_imageId;
import static com.example.instacart.MainActivity.EXTRA_productName;
import static com.example.instacart.MainActivity.EXTRA_productPrice;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartAdapter adapter;
    private ArrayList<ProductItems> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        Intent intent = new Intent(CartActivity.this, ItemDetailsActivity.class);
//        startActivityForResult(intent, 1);
        productList.add(new ProductItems(R.drawable.avocado, "apple", "12"));
        setRecyclerView();

    }

    public void setRecyclerView() {
        recyclerView = findViewById(R.id.rv_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        adapter = new CartAdapter(productList);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickLister(new CartAdapter.OnProductListener() {
            @Override
            public void onProductClick(ProductItems productItems) {
                Intent intent = new Intent(CartActivity.this, ItemDetailsActivity.class);
                intent.putExtra(EXTRA_productName, productItems.getNameProduct());
                intent.putExtra(EXTRA_productPrice, productItems.getPriceProduct());
                intent.putExtra(EXTRA_imageId, productItems.getImageResourceProduct());
                //intent.putExtra(EXTRA_counterItem , itemDetailsActivity.counterItem);
                startActivity(intent);
                Toast.makeText(CartActivity.this, productItems.getPriceProduct(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}