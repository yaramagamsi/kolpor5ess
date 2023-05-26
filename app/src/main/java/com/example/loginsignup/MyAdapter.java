package com.example.loginsignup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> productArrayList;
    ItemClickListener mItemListener;

    public MyAdapter(Context context, ArrayList<Product> productArrayList, ItemClickListener itemClickListener) {
        this.context = context;
        this.productArrayList = productArrayList;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.product,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Product product = productArrayList.get(position);

        holder.ItemName.setText(product.getName());
        holder.Price.setText(String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(view -> {

            mItemListener.onItemClick(productArrayList.get(position));
        } );
    }



    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public interface ItemClickListener{

        void onItemClick(Product product);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ItemName, Price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.tvUsername);
            Price = itemView.findViewById(R.id.tvMessage);
        }
    }
}
