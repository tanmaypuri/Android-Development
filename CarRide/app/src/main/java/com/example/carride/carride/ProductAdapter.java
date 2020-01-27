package com.example.carride.carride;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
       View view = inflater.inflate(R.layout.list_layout,null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        Product product = productList.get(i);

        productViewHolder.tvName.setText(product.getName());
        productViewHolder.tvGender.setText(product.getGender());
        productViewHolder.tvContact.setText(product.getPhone());
        productViewHolder.tvLeaving.setText(product.getLeaving());
        productViewHolder.tvGoing.setText(product.getGoing());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvGender, tvContact, tvLeaving, tvGoing;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvContact = itemView.findViewById(R.id.tvContact);
            tvLeaving = itemView.findViewById(R.id.tvLeaving);
            tvGoing = itemView.findViewById(R.id.tvGoing);
        }


    }

}
