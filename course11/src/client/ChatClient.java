package client;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    private final String serverAddress;
    private final int serverport;
    private Socket socket;

    public ChatClient(String serverAddress, int serverport) {
        this.serverAddress = serverAddress;
        this.serverport = serverport;
    }

    public ChatClient() {
        this.serverAddress = "localhost";
        this.serverport = 8088;
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        if (client.connect()) {
            System.out.println("Client connected");

        }
    }

    private boolean connect() {
        try {
            socket = new Socket(serverAddress, serverport);
            return true;
        } catch (IOException e) {
            System.err.println("Connection failed");
            return false;
        }
    }
}
