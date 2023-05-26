import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);

        // Bind the message data to the views in the ViewHolder
        holder.senderTextView.setText(message.getSender());
        holder.recipientTextView.setText(message.getRecipient());
        holder.contentTextView.setText(message.getContent());
        holder.statusTextView.setText(message.isRead() ? "Read" : "Unread");
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // Create a ViewHolder class to hold references to the views in the message_item.xml layout
    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        TextView recipientTextView;
        TextView contentTextView;
        TextView statusTextView;

        MessageViewHolder(View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.senderTextView);
            recipientTextView = itemView.findViewById(R.id.recipientTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}