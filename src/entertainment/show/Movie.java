package entertainment.show;

import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Show {
    private final int duration;

    private final List<Double> ratingMovie;

    public Movie(final MovieInputData movieInputData) {
        super(movieInputData);
        this.duration = movieInputData.getDuration();
        this.ratingMovie = new ArrayList<>();
    }

    /**
     * @return the average grade of a movie
     */
    @Override
    public Double averageGrade() {
        if (ratingMovie.isEmpty()) {
            return 0.0;
        }

        Double sum = 0.0;
        for (Double rating: ratingMovie) {
            sum += rating;
        }
        return sum / ratingMovie.size();
    }

    /**
     * @return the duration of a movie
     */
    @Override
    public int overAllDuration() {
        return duration;
    }

    /**
     * duration getter
     */
    public int getDuration() {
        return duration;
    }

    /**
     * movie rating getter
     */
    public List<Double> getRatingMovie() {
        return ratingMovie;
    }
}
