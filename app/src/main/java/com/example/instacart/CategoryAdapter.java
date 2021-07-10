package com.example.instacart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private ArrayList<ProductItems> mCategoryItemsList;
    private OnCategoryListener listener;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCategory;
        public TextView tvCategoryName;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategory = itemView.findViewById(R.id.iv_category);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onCategoryClick(mCategoryItemsList.get(position));
                    }
                }
            });
        }
    }


    public CategoryAdapter(ArrayList<ProductItems> productItemsList) {
        mCategoryItemsList = productItemsList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        CategoryViewHolder pvh = new CategoryViewHolder(view);
        return pvh;

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        ProductItems currentItem = mCategoryItemsList.get(position);
        holder.ivCategory.setImageResource(currentItem.getImageResourceAllCategory());
        holder.tvCategoryName.setText(currentItem.getAllCategoryName());

    }

    @Override
    public int getItemCount() {
        return mCategoryItemsList.size();
    }

    public interface OnCategoryListener {
        void onCategoryClick(ProductItems productItems);
    }

    public void setOnItemClickLister(OnCategoryListener listener) {
        this.listener = listener;

    }
}
