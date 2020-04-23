package singleton;

public class EagerSingleton {
    private static EagerSingleton ourInstance = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return ourInstance;
    }

    private EagerSingleton() {
    }
}
