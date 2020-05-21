package client;

import java.io.*;
import java.net.Socket;

public class WriteTask implements Runnable {

    private PrintWriter pr;
    private Socket socket;
    private BufferedReader keyboard;

    public WriteTask(Socket socket) {
        this.socket = socket;
        try {
            OutputStream os = socket.getOutputStream();
            pr = new PrintWriter(os, true);
            keyboard = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.err.println("Error getting output stream: "+ e.getMessage());
        }
    }

    @Override
    public void run() {
        String command = "";
        do {
            System.out.println("> ");
            try {
                command = keyboard.readLine();
                pr.println(command);
            } catch (IOException e) {
                System.err.println("Error writing to server" + e.getMessage());
            }
        } while (!command.equalsIgnoreCase("logout"));
    }
}
