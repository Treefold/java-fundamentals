public class TestTask {
    public static void main(String[] args) {
        Task t1 = new OutTask ("Msg");
        Task t2 = new RandomOutTask();
        Task t3 = new CounterOutTask();
        System.out.println("Test tasks");
        t3.DoYourJob(); // cnt = 1
        t1.DoYourJob(); // msg
        t1.DoYourJob(); // msg
        t3.DoYourJob(); // cnt = 2
        t2.DoYourJob(); // rand
        t2.DoYourJob(); // rand
        t3.DoYourJob(); // cnt = 3
        System.out.println("End of Test tasks\n");

        System.out.println("Test Stack");
        Stack s = new Stack(11);
        s.add (t3);
        s.add (t2);
        Stack ss = new Stack(s);
        s.perform(); // rand
        s.add (t3);
        s.add (t2);
        s.add (t1);
        ss.add (t1);
        ss.add (t3);  // should cnt = 4
        ss.abandon(); // but it is eliminated
        ss.abandon(); // msg eliminated
        ss.add (new RandomOutTask());
        ss.add (t3);
        System.out.println("Free s");
        while (!s.isEmpty()) { // msg, rand, cnt=4, cnt=5
            s.perform();
        }
        System.out.println("Free ss");
        while (!ss.isEmpty()) {// cnt=6, rand, new rand, cnt=7
            ss.perform();
        }
        System.out.println("End of Test Stack\n");

        System.out.println("Test Queue");
        Queue q = new Queue(11);
        q.add (t3);
        q.add (t2);
        Queue qq = new Queue(q); // rand
        q.perform(); // cnt = 8
        q.add (t3);
        q.add (t2);
        q.add (t1);
        qq.add (t1);
        qq.add (t3);
        qq.abandon(); // cnt eliminated from qq
        qq.add (new RandomOutTask());
        qq.add (t3);
        System.out.println("Free q");
        while (!q.isEmpty()) { // rand, cnt=9, rand, msg
            q.perform();
        }
        System.out.println("Free qq");
        while (!qq.isEmpty()) {// rand, msg, cnt=10, new rand, cnt=11
            qq.perform();
        }
        System.out.println("End of Test Queue\n");
    }
}
