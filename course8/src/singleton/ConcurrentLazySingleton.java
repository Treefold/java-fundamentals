package singleton;

public class ConcurrentLazySingleton {
    private static ConcurrentLazySingleton instance;

    public static ConcurrentLazySingleton getInstance() {
        if (instance == null) {
            synchronized (ConcurrentLazySingleton.class) {
                if (instance == null) {
                    instance = new ConcurrentLazySingleton();
                }
            }
        }
        return instance;
    }

    private ConcurrentLazySingleton() {
    }
}
