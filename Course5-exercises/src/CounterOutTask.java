public class CounterOutTask implements Task{

    public static int cnt = 0;

    @Override
    public void DoYourJob() {
        System.out.println(++cnt);
    }
}
