public class Ex3 {
    public static void clean(Washable washable) {
        washable.wash();
    }

    public static void main(String[] args) {
        Cup cup = new TeaCup();
        clean(cup);

        Car car= new Car();
        clean(car);

        Washable washable = new Washable() {
            @Override
            public void wash() {
                System.out.println("wash anonymus object");
            }
        };
        clean (washable);

        Car w1 = new Car() {
            @Override
            public void wash() {
                System.out.println("new car wash");
            }
        };
        clean(w1);
    }
}
