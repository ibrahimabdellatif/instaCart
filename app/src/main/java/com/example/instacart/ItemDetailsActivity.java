package com.example.instacart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.instacart.MainActivity.EXTRA_imageId;
import static com.example.instacart.MainActivity.EXTRA_productName;
import static com.example.instacart.MainActivity.EXTRA_productPrice;

public class ItemDetailsActivity extends AppCompatActivity {

    private Button btnPlus;
    private Button btnMinim;
    private Button addToCart;
    private ImageView ivProductImage;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private ProductItems productItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        setTitle("product details");
//
//        productItems  = (ProductItems) getIntent().getSerializableExtra("product item");
//        int imgID =this.getResources().getIdentifier(String.valueOf(productItems.getImageResourceProduct()), "drawable" , this.getPackageName());
//
//        Glide.with(this).load(imgID).into(ivProductImage);


        Intent intent = getIntent();
        String imageResource = intent.getStringExtra(EXTRA_imageId);
        String produceName = intent.getStringExtra(EXTRA_productName);
        String productPrice = intent.getStringExtra(EXTRA_productPrice);

        ivProductImage = findViewById(R.id.iv_product_image);
        tvProductName = findViewById(R.id.tv_product_name);
        tvProductPrice = findViewById(R.id.tv_product_price);

        Glide.with(this).load(imageResource).into(ivProductImage);
       // tvProductName.setText(produceName);
       // tvProductPrice.setText(productPrice);

    }

}