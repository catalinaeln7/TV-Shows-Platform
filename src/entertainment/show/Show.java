package entertainment.show;

import database.Database;
import fileio.ShowInput;
import user.User;

import java.util.ArrayList;

public abstract class Show {

    protected final String title;

    protected final int year;

    protected final ArrayList<String> cast;

    protected final ArrayList<String> genres;

    public Show(final ShowInput showInput) {
        this.title = showInput.getTitle();
        this.year = showInput.getYear();
        this.cast = new ArrayList<>(showInput.getCast());
        this.genres = new ArrayList<>(showInput.getGenres());
    }

    /**
     * @return average grade of a video
     */
    public abstract Double averageGrade();

    /**
     * @return overall duration of a video
     */
    public abstract int overAllDuration();

    /**
     * @return number of times a video was added as favorite by users
     */
    public int nrOfAddedAsFavorite() {
        int nrFav = 0;
        for (User user: Database.getDatabase().getUsers()) {
            if (user.getFavoriteMovies().contains(title)) {
                nrFav++;
            }
        }
        return nrFav;
    }

    /**
     * @return number of times a video was viewed
     */
    public int nrViews() {
        int nrViews = 0;
        for (User user: Database.getDatabase().getUsers()) {
            if (user.getHistory().containsKey(title)) {
                nrViews += user.getHistory().get(title);
            }
        }
        return nrViews;
    }

    /**
     * @return displays all the shows by title
     */
    @Override
    public String toString() {
        return title;
    }

    /**
     * title getter
     */
    public String getTitle() {
        return title;
    }

    /**
     * year getter
     */
    public int getYear() {
        return year;
    }

    /**
     * cast getter
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * genres getter
     */
    public ArrayList<String> getGenres() {
        return genres;
    }
}
