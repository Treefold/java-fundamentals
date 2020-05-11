package workwiththreads;

import static java.lang.Thread.sleep;

public class Processor implements Runnable{
    @Override
    public void run() {
        accessCommonResources();
    }

    private static synchronized void accessCommonResources() {
        while (true) {
            System.out.println("Hello " + Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
