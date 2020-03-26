public class Cleaner {
    public static void cleanObject (Object obj) {
        if (obj instanceof Car) {
            ((Car) obj).wash();
        }
        else if (obj instanceof Window) {
            ((Window) obj).wash();
        }
        else if (obj instanceof Cup) {
            ((Cup) obj).wash();
        }
        else if (obj instanceof Dog) {
            ((Dog) obj).wash();
        }
        else {
            throw new RuntimeException("Unknwn Object Type to clean");
        }
    }

    public static void clean (WashableObject washableObject) {
        washableObject.wash();
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        cleanObject(dog);
//        clean(dog);

        Window window = new Window();
        cleanObject(window);
//        clean(window);

        Car car = new Car();
        cleanObject(car);
//        clean (car);

        Cup cup = new TeaCup();
        cleanObject(cup);
//        clean (cup);

        String unknownType = "unknown type";
        cleanObject(unknownType);
//        clean(unknownType);
    }
}
