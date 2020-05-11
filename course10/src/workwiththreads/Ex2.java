package workwiththreads;

import java.util.stream.Stream;

public class Ex2 {
    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable();
        Thread t1 = new Thread(r1);
        t1.start();

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                Stream.iterate(0, i->i+2).limit(10).forEach(i-> System.out.println(i +" on thread " + Thread.currentThread().getName()));
            }
        };
        Thread t2 = new Thread(r2);
        t2.start();

        Runnable r3 = () -> System.out.println(" My runnable r3");
        new Thread(r3).start();

        new Thread(() -> System.out.println(" My runnable r4"), "t4").start();
        new Thread(() -> System.out.println(" My runnable r5 is run by " + Thread.currentThread().getName()), "t5").run();

    }
}
