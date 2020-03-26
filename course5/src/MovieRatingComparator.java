import java.util.Comparator;

public class MovieRatingComparator implements Comparator <Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return (int) (o2.getRating()*100 - o1.getRating()*100);
    }
}
