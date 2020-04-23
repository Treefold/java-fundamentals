package lambda.ex1;

public class Ex1 {
    public static void main(String[] args) {
        Instrument guitar = new Guitar();

        Instrument piano = new Instrument() {
            @Override
            public void play() {
                System.out.println("Playing piano");
            }
        };

        guitar.play();
        piano.play();

        Instrument i1 = () -> System.out.println("New instrument");
        i1.play();
    }
}
