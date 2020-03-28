public class OutTask implements Task {

    private String msg;

    public OutTask(String msg) {
        this.msg = msg;
    }

    @Override
    public void DoYourJob () {
        System.out.println(msg);
    }
}
