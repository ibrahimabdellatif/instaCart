package com.example.instacart;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instacart.Adapter.CartAdapter;
import com.example.instacart.db.ProductViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CartAdapter adapter;
    private Button btnCheckout;
    private List<ProductItems> productList = new ArrayList<>();
    private ItemDetailsActivity detailsActivity = new ItemDetailsActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        detailsActivity.productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        detailsActivity.productViewModel.getAllProduct().observe(this, new Observer<List<ProductItems>>() {
            @Override
            public void onChanged(List<ProductItems> productItems) {
                adapter.setProductItem((ArrayList<ProductItems>) productItems);
            }
        });

        setRecyclerView();
        setBtnCheckout();

    }


    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter((ArrayList<ProductItems>) productList);
        adapter.setOnItemClickLister(new CartAdapter.OnProductListener() {
            @Override
            public void onProductClick(ProductItems productItems) {
                Toast.makeText(CartActivity.this, productItems.getNameProduct(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);


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

    void swipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                detailsActivity.productViewModel.delete(adapter.getProductItemAt(viewHolder.getAdapterPosition()));
                Toast.makeText(CartActivity.this, "Product is Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_all_product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_product_menu:
                detailsActivity.productViewModel.deleteAllProduct();
                Toast.makeText(this, "cart be empty", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}