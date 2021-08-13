package com.example.instacart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.instacart.db.ProductViewModel;

import static com.example.instacart.MainActivity.EXTRA_imageId;
import static com.example.instacart.MainActivity.EXTRA_productName;
import static com.example.instacart.MainActivity.EXTRA_productPrice;

public class ItemDetailsActivity extends AppCompatActivity {

    private int imageResource;
    private String productName;
    private String productPrice;
    public ProductViewModel productViewModel;

    private Button btnPlus;
    private Button btnMinim;
    private Button addToCart;
    private ImageView ivProductImage;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private TextView tvNumberOfItems;
    public int counterItem;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        setTitle("product details");
        initiateValues();

    }

    public void initiateValues() {
        //this is our data that we will send it to room and show it in shopping cart
        Intent intent = getIntent();
        imageResource = intent.getIntExtra(EXTRA_imageId, 0);
        productName = intent.getStringExtra(EXTRA_productName);
        productPrice = intent.getStringExtra(EXTRA_productPrice);


        ivProductImage = findViewById(R.id.iv_product_image);

        tvProductName = findViewById(R.id.tv_name);
        tvProductPrice = findViewById(R.id.tv_price);
        tvNumberOfItems = findViewById(R.id.tv_number_of_items);

        btnMinim = findViewById(R.id.btn_min);
        btnPlus = findViewById(R.id.btn_plus);
        addToCart = findViewById(R.id.btn_add_to_cart);

        ivProductImage.setImageResource(imageResource);
        tvProductName.setText(productName);
        tvProductPrice.setText(productPrice);


        btnMinim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counterItem > 1) {
                    counterItem--;
                    tvNumberOfItems.setText(counterItem + "");
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterItem++;
                tvNumberOfItems.setText(counterItem + "");

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
                // Toast.makeText(ItemDetailsActivity.this, "Add To Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addToCart() {
        //get data form main activity
        Intent intent = getIntent();
        imageResource = intent.getIntExtra(EXTRA_imageId, 0);
        productName = intent.getStringExtra(EXTRA_productName);
        productPrice = intent.getStringExtra(EXTRA_productPrice);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        ProductItems product = new ProductItems(imageResource, productName, productPrice);
        productViewModel.insert(product);

        Toast.makeText(this, "save successfully", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();

    }
}