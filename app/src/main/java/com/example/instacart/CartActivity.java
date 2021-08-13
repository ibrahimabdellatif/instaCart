package com.example.instacart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instacart.Adapter.CartAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartAdapter adapter;
    private Button btnCheckout;
    private ArrayList<ProductItems> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        cartItemWithFakeData();
        setRecyclerView();
        setBtnCheckout();

    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(productList);
        adapter.setOnItemClickLister(new CartAdapter.OnProductListener() {
            @Override
            public void onProductClick(ProductItems productItems) {
                Toast.makeText(CartActivity.this, productItems.getNameProduct(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);


    }

    private void cartItemWithFakeData() {
        productList.add(new ProductItems(R.drawable.apple, "Organic Apple", "$16.56"));
        productList.add(new ProductItems(R.drawable.banana, "Organic Banana(each)", "$15.00"));
        productList.add(new ProductItems(R.drawable.avocado, "Organic Hass Avocado", "$23.65"));
        productList.add(new ProductItems(R.drawable.strawberrypng, "Juicy Strawberry", "$20.65"));

    }

    private void setBtnCheckout() {
        btnCheckout = findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this, "checkout is successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

}