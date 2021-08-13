package com.example.instacart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder> {

    private ArrayList<ProductItems> mProductItemsList;
    private OnProductListener listener;

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCart;
        public TextView tvCartProductName;
        public TextView tvCartProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCart = itemView.findViewById(R.id.iv_cart);
            tvCartProductName = itemView.findViewById(R.id.tv_cart_product_name);
            tvCartProductPrice = itemView.findViewById(R.id.tv_cart_product_price);

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
        holder.ivCart.setImageResource(currentItem.getImageResourceProduct());
        holder.tvCartProductName.setText(currentItem.getNameProduct());
        holder.tvCartProductPrice.setText(currentItem.getPriceProduct());
    }

    @Override
    public int getItemCount() {
        return mProductItemsList.size();
    }

    public interface OnProductListener {
        void onProductClick(ProductItems productItems);
    }

    public void setOnItemClickLister(OnProductListener listener) {
        this.listener = listener;

    }
}
