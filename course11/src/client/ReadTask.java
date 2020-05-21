package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadTask implements Runnable {

    private BufferedReader br;
    private Socket socket;

    public ReadTask(Socket socket) {
        this.socket = socket;
        try {
            InputStream inputStream = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            System.err.println("Error getting input stream: "+ e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                readMessagesFromServer();
            } catch (IOException e) {
                System.err.println("Error reading from server: "+ e.getMessage());
            }
        }
    }

    private void readMessagesFromServer() throws IOException {
        String line;
        while ((line = br.readLine()) != null) {

        }
    }
}
