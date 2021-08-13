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

import static com.example.instacart.MainActivity.EXTRA_imageId;
import static com.example.instacart.MainActivity.EXTRA_productName;
import static com.example.instacart.MainActivity.EXTRA_productPrice;

public class ItemDetailsActivity extends AppCompatActivity {

    private ProductItems productItems;
    private int imageResource;
    private String productName;
    private String productPrice;
    public static final String extra_productName = "cartProductName";
    public static final String extra_productPrice = "cartProductPrice";
    public static final String extra_imageId = "cartImageResource";
    public static final String extra_id = "EXTRA_ID";


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
//                finish();
            }
        });
    }

    public void addToCart() {
        Intent intent = getIntent();
        int imageResource = intent.getIntExtra(EXTRA_imageId, 0);
        String productName = intent.getStringExtra(EXTRA_productName);
        String productPrice = intent.getStringExtra(EXTRA_productPrice);

        Intent data = new Intent();
        data.putExtra(extra_imageId, imageResource);
        data.putExtra(extra_productName, productName);
        data.putExtra(extra_productPrice, productPrice);

        int id = getIntent().getIntExtra(extra_id, -1);
        if (id != -1) {
            data.putExtra(extra_id, id);
        }
        setResult(RESULT_OK, data);
        Toast.makeText(this, "add to cart", Toast.LENGTH_SHORT).show();

    }


}