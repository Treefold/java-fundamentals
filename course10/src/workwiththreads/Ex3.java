package workwiththreads;

import java.sql.SQLOutput;

public class Ex3 {
    public static void main(String[] args) throws InterruptedException {
        for (Thread.State ts : Thread.State.values()){
            System.out.println(ts);
        }

        Thread t1 = new Thread(new Processor(), "Thread1");
        Thread t2 = new Thread(new Processor(), "Thread2");
        t1.start();
        t2.start();

        Thread.sleep (10000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }
}
