package com.example.instacart;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instacart.Adapter.CategoryAdapter;
import com.example.instacart.Adapter.ProductAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_productName = "productName";
    public static final String EXTRA_productPrice = "productPrice";
    public static final String EXTRA_imageId = "imageResource";
    public static final String EXTRA_counterItem = "counterItem";

    private RecyclerView recyclerViewSnacks;
    private RecyclerView recyclerViewFreshProduct;
    private RecyclerView recyclerViewAllCategories;
    private RecyclerView recyclerViewCart;

    private RecyclerView.LayoutManager layoutManager;
    private ProductAdapter adapter;
    private CategoryAdapter categoryAdapter;

    private ItemDetailsActivity itemDetailsActivity;
    private ArrayList<ProductItems> productList = new ArrayList<>();
    private ArrayList<ProductItems> allCategoriesList = new ArrayList<>();
    private ArrayList<ProductItems> cartList = new ArrayList<>();

    private Button btnViewMoreFreshProduct;
    private Button btnViewMoreSnacks;
    private Button btnViewMoreAllCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createProductItems();
        createAllCategories();
        recyclerViewFreshProduct();
        recyclerViewSnacks();
        recyclerViewAllCategories();


    }

    public void createProductItems() {

        productList.add(new ProductItems(R.drawable.apple, "Organic Apple", 16.56, 0, 0));
        productList.add(new ProductItems(R.drawable.banana, "Organic Banana(each)", 15.00, 0, 0));
        productList.add(new ProductItems(R.drawable.avocado, "Organic Hass Avocado", 23.65, 0, 0));
        productList.add(new ProductItems(R.drawable.strawberrypng, "Juicy Strawberry", 20.65, 0, 0));

    }

    public void recyclerViewFreshProduct() {
        recyclerViewFreshProduct = findViewById(R.id.rv_fresh_product);
        recyclerViewFreshProduct.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        adapter = new ProductAdapter(productList);
        recyclerViewFreshProduct.setLayoutManager(layoutManager);
        adapter.setOnItemClickLister(new ProductAdapter.OnProductListener() {
            @Override
            public void onProductClick(ProductItems productItems) {

                Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                intent.putExtra(EXTRA_productName, productItems.getName());
                intent.putExtra(EXTRA_productPrice, productItems.getPrice());
                intent.putExtra(EXTRA_imageId, productItems.getImage());
                //intent.putExtra(EXTRA_counterItem , itemDetailsActivity.counterItem);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, productItems.getNameProduct(), Toast.LENGTH_SHORT).show();
            }
        });

        btnViewMoreFreshProduct = findViewById(R.id.btn_view_more_fresh_product);
        btnViewMoreFreshProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllProductActivity.class);
                startActivity(intent);
            }
        });
        recyclerViewFreshProduct.setAdapter(adapter);
    }

    public void recyclerViewSnacks() {
        recyclerViewSnacks = findViewById(R.id.rv_snacks);
        recyclerViewSnacks.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        adapter = new ProductAdapter(productList);
        recyclerViewSnacks.setLayoutManager(layoutManager);
        adapter.setOnItemClickLister(new ProductAdapter.OnProductListener() {
            @Override
            public void onProductClick(ProductItems productItems) {
                Intent intent = new Intent(MainActivity.this, ItemDetailsActivity.class);
                intent.putExtra(EXTRA_productName, productItems.getName());
                intent.putExtra(EXTRA_productPrice, productItems.getPrice());
                intent.putExtra(EXTRA_imageId, productItems.getImage());
                startActivity(intent);
                //Toast.makeText(MainActivity.this, productItems.getNameProduct(), Toast.LENGTH_SHORT).show();
            }
        });

        btnViewMoreSnacks = findViewById(R.id.btn_view_more_snacks);
        btnViewMoreSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllProductActivity.class);
                startActivity(intent);
            }
        });
        recyclerViewSnacks.setAdapter(adapter);
    }


    public void recyclerViewAllCategories() {
        recyclerViewAllCategories = findViewById(R.id.rv_all_categories);
        recyclerViewAllCategories.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        categoryAdapter = new CategoryAdapter((allCategoriesList));
        recyclerViewAllCategories.setLayoutManager(layoutManager);
        categoryAdapter.setOnItemClickLister(new CategoryAdapter.OnCategoryListener() {
            @Override
            public void onCategoryClick(ProductItems productItems) {
                Toast.makeText(MainActivity.this, productItems.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btnViewMoreAllCategories = findViewById(R.id.btn_view_more_all_categories);
        btnViewMoreAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllProductActivity.class);
                startActivity(intent);
            }
        });
        recyclerViewAllCategories.setAdapter(categoryAdapter);
    }

    public void createAllCategories() {
        allCategoriesList.add(new ProductItems(R.drawable.milk, "Drink"));
        allCategoriesList.add(new ProductItems(R.drawable.vegetable, "Vegetables"));
        allCategoriesList.add(new ProductItems(R.drawable.fruits, "Fruits"));
        allCategoriesList.add(new ProductItems(R.drawable.bread, "Bakery"));
        allCategoriesList.add(new ProductItems(R.drawable.milk, "Drink"));
        allCategoriesList.add(new ProductItems(R.drawable.vegetable, "Vegetables"));
        allCategoriesList.add(new ProductItems(R.drawable.fruits, "Fruits"));
        allCategoriesList.add(new ProductItems(R.drawable.bread, "Bakery"));
    }

    //this method for make save icon menu work and save new items.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.shopping_cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Welcome in cart", Toast.LENGTH_LONG).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}