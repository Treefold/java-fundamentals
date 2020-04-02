package exceptii.ex3;

public class R2 implements AutoCloseable {
    String name;

    public R2(String name) {
        this.name = name;
        System.out.println("Opening resource " + name);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing resource " + name);
        throw new Exception("exception while closing " + name);
    }
}
