public class Cup implements BubbleBathable {
    double volume;
    String color;

    public int getLevelOfFragility () {
        return Washable.FRAGILE;
    }

    @Override
    public boolean needsWash () {
        return false;
    }

    @Override
    public void wash() {
        if (needsWash()) {
            System.out.println("Washing a cup");
        }
    }

    @Override
    public  void soak() {}

    @Override
    public void takeBubbleBath() {}

    @Override
    public void scrub() {}
}
