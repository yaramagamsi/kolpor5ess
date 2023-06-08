package com.example.loginsignup.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsignup.Classes.User;
import com.example.loginsignup.databinding.ItemContainerUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private final List<User> users;

   private UsersAdapter.ItemClickListener uItemListener;

    public UsersAdapter(ArrayList<User> users, ItemClickListener itemClickListener) {
        this.users = users;
        this.uItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UserViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.setUserDate(users.get(position));

        holder.itemView.setOnClickListener(view -> {
            uItemListener.onItemClickUser(users.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public interface ItemClickListener {
        void onItemClickUser(User user);

        void onItemClick(com.google.firebase.firestore.auth.User user);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        ItemContainerUserBinding binding;

        UserViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserDate(User user) {
            binding.textName.setText(user.getName());
            binding.textEmail.setText(user.getAddress());
            binding.getRoot().setOnClickListener(v -> uItemListener.onItemClickUser(user));
         //   binding.imageProfile.setImageBitmap(getUserImage(user.getPhoto()));
        }
    }

   // private Bitmap getUserImage(String encodedImage) {

   //    byte[] bytes = Base64.getDecoder().decode(encodedImage, Base64.DEFAULT);
   //    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
  //  }
}
