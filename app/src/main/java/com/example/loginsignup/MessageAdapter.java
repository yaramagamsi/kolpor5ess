package com.example.loginsignup;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginsignup.Classes.Product;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    Context context;
    ArrayList<Message> messageArrayList;
    MessageAdapter.ItemClickListener mItemListener;

    public MessageAdapter(Context context, ArrayList<Message> messageArrayList, MessageAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.messageArrayList = messageArrayList;
        this.mItemListener = itemClickListener;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.messages,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {

        Message message = messageArrayList.get(position);

        holder.username.setText(message.arg1);
        holder.NewMessage.setText(message.arg2);

        holder.itemView.setOnClickListener(view -> {

            mItemListener.onItemClick(messageArrayList.get(position));
        } );

    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public interface ItemClickListener{

        void onItemClick(Message message);

        void onItemClick(Product product);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username, NewMessage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.tvUsername);
            NewMessage = itemView.findViewById(R.id.tvMessage);
        }
    }
}