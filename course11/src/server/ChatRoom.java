package server;

public class ChatRoom {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(8088);
        new Thread (chatServer).start();
    }
}
