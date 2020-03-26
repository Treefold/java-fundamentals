public class Dog extends Animal implements BubbleBathable {
    @Override
    public void wash () {
        System.out.println("washing a dog");
    }

    @Override
    public  void soak() {}

    @Override
    public void takeBubbleBath() {}

    @Override
    public void scrub() {}
}
