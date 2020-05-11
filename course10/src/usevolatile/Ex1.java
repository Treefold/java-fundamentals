package usevolatile;

public class Ex1 {

    private static volatile int outer;

    public static void main(String[] args) throws InterruptedException{
        Thread rt = new Thread(new ReaderTask(), "reader");
        Thread wt = new Thread(new WriterTask(), "writer");
        rt.start();
        wt.start();
        rt.join();
        wt.join();
    }

    static class ReaderTask implements Runnable {
        @Override
        public void run() {
            int local = outer;
            System.out.println("Local value from " + Thread.currentThread().getName() + " is " + local);
            while (local < 3) {
                if (local != outer) {
                    local = outer;
                    System.out.println("Local value from " + Thread.currentThread().getName() + " is " + local);
                }
            }
        }
    }

    static class WriterTask implements Runnable {
        @Override
        public void run() {
            int local = outer;
            System.out.println("Local value from " + Thread.currentThread().getName() + " is " + local);
            while (outer < 3) {
                outer = ++local;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
