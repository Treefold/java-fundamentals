package workwiththreads;

public class Ex1 {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();

        Thread t2 = new MyThread();
        t2.start();

        Thread t3 = new Thread () {
            public void run() {
                this.setName("My Thread");
                for (int i = 0; i < 10; ++i) {
                    System.out.println(this.getName() + " step " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t3.start();
    }
}
