package frontend;

public class App {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        try {
            mainFrame.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
