package com.example.loginsignup.Adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginsignup.Classes.Product;
import com.example.loginsignup.FirebaseServices;
import com.example.loginsignup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> productArrayList;
    ItemClickListener mItemListener;

    FirebaseServices fbs;
    ArrayList<String> productListPath;

    public MyAdapter(Context context, ArrayList<Product> productArrayList, ArrayList<String> productListPath , ItemClickListener itemClickListener) {
        this.context = context;
        this.productArrayList = productArrayList;
        this.mItemListener = itemClickListener;
        this.productListPath = productListPath;
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

        fbs = FirebaseServices.getInstance();
        holder.ItemName.setText(product.getName());
        holder.Price.setText(String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(view -> {

            mItemListener.onItemClick(productArrayList.get(position));
        } );

        StorageReference storageRef = fbs.getStorage().getReference().child(product.getPhoto());
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context)
                        .load(uri)
                        .into(holder.image);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }



    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public interface ItemClickListener{

        void onItemClick(Product product);

        void onItemClick(Message message);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ItemName, Price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.tvUsername);
            Price = itemView.findViewById(R.id.tvMessage);
            image = itemView.findViewById(R.id.imageAdapter);
        }
    }
}
