package com.example.instacart.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instacart.ProductItems;
import com.example.instacart.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder> {

    private ArrayList<ProductItems> mProductItemsList;
    private OnProductListener listener;

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProduct;
        public TextView tvProductName;
        public TextView tvProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_cart);
            tvProductName = itemView.findViewById(R.id.tv_cart_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_cart_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onProductClick(mProductItemsList.get(position));
                    }
                }
            });
        }
    }


    public CartAdapter(ArrayList<ProductItems> productItemsList) {
        mProductItemsList = productItemsList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        ProductViewHolder pvh = new ProductViewHolder(view);
        return pvh;

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItems currentItem = mProductItemsList.get(position);
        holder.ivProduct.setImageResource(currentItem.getImageResourceProduct());
        holder.tvProductName.setText(currentItem.getNameProduct());
        holder.tvProductPrice.setText(currentItem.getPriceProduct());
    }

    @Override
    public int getItemCount() {
        return mProductItemsList.size();
    }

    public void setProductItem(ArrayList<ProductItems> product) {
        this.mProductItemsList = product;
        notifyDataSetChanged();
    }

    public ProductItems getProductItemAt(int position) {
        return mProductItemsList.get(position);
    }

    public interface OnProductListener {
        void onProductClick(ProductItems productItems);
    }

    public void setOnItemClickLister(OnProductListener listener) {
        this.listener = listener;

    }
}
