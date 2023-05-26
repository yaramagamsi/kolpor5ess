public class Message {
    private String sender;
    private String recipient;
    private String content;
    private boolean isRead;

    public Message(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.isRead = false;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        isRead = true;
    }

    public void display() {
        System.out.println("From: " + sender);
        System.out.println("To: " + recipient);
        System.out.println("Content: " + content);
        System.out.println("Status: " + (isRead ? "Read" : "Unread"));
    }
}