package com.example.loginsignup.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsignup.Classes.Product;
import com.example.loginsignup.Classes.User;
import com.example.loginsignup.FirebaseServices;
import com.example.loginsignup.R;
import com.example.loginsignup.databinding.ItemContainerUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    Context context;
    ArrayList<User> userArrayList;
    UsersAdapter.ItemClickListener uItemListener;

    FirebaseServices fbs;
    ArrayList<String> userListPath;
    public UsersAdapter(Context context, ArrayList<User> userArrayList, UsersAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.userArrayList = userArrayList;
        this.uItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.messages,parent,false);


        return new UsersAdapter.UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

       User user = userArrayList.get(position);

        fbs = FirebaseServices.getInstance();
        holder.Name.setText(user.getName());
        holder.Email.setText(String.valueOf(user.getAddress()));

        holder.itemView.setOnClickListener(view -> {

            uItemListener.onItemClick(userArrayList.get(position));
        } );
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public interface ItemClickListener {
        void onItemClickUser(User user);

        void onItemClick(com.google.firebase.firestore.auth.User user);

        void onItemClick(User user);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {


        TextView Name, Email;
        ImageView image;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tvUsername);
            Email = itemView.findViewById(R.id.tvMessage);
          //  image = itemView.findViewById(R.id.imageAdapter);
        }
        //ItemContainerUserBinding binding;

        /*
        UserViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserDate(User user) {
            binding.textName.setText(user.getName());
            binding.textEmail.setText(user.getAddress());
            binding.getRoot().setOnClickListener(v -> uItemListener.onItemClickUser(user));
         //   binding.imageProfile.setImageBitmap(getUserImage(user.getPhoto()));
        } */
    }

   /* private Bitmap getUserImage(String encodedImage) {

       byte[] bytes = Base64.getDecoder().decode(encodedImage, Base64.DEFAULT);
       return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
   }

    */
}
