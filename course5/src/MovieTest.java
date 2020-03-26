import javax.sound.midi.Soundbank;
import java.util.Arrays;

public class MovieTest {
    public static void main(String[] args) {
        Movie[] movies = {new Movie("Breaking bad", 2014, 9.4),
                          new Movie("Star wars",    1977, 8.8),
                          new Movie("Die Hard",     1990, 8),
                          new Movie("Terminator",   1994, 7.5)};
        System.out.println(Arrays.toString(movies));

        Arrays.sort(movies);
        System.out.println(Arrays.toString(movies));

        Arrays.sort(movies, new MovieRatingComparator());
        System.out.println(Arrays.toString(movies));

        Arrays.sort(movies, new MovieNameComparator());
        System.out.println(Arrays.toString(movies));
    }
}
