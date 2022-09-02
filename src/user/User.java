package user;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private final String username;

    private final String subscriptionType;

    private final Map<String, Integer> history;

    private final ArrayList<String> favoriteMovies;

    private final Map<String, Map<Integer, Double>> ratingSerial;

    private final Map<String, Double> ratingMovie;

    private int numberOfRatings;

    public User(final UserInputData userInputData) {
        this.username = userInputData.getUsername();
        this.subscriptionType = userInputData.getSubscriptionType();
        this.history = new HashMap<>(userInputData.getHistory());
        this.favoriteMovies = new ArrayList<>(userInputData.getFavoriteMovies());
        this.ratingSerial = new HashMap<>();
        this.ratingMovie = new HashMap<>();
        this.numberOfRatings = 0;
    }

    /**
     * @param title title of video
     * @return true if video is already viewed
     */
    public boolean hasSeen(final String title) {
        return history.containsKey(title);
    }

    /**
     * @param title title of video
     * @return true if video is already added as favorite
     */
    public boolean wasAddedAsFavorite(final String title) {
        return favoriteMovies.contains(title);
    }

    /**
     * @param title title of video
     * @param seasonNumber number of season
     * @return true if user has rated video
     */
    public boolean hasRated(final String title, final Integer seasonNumber) {
        return ((ratingSerial.containsKey(title)
                && ratingSerial.get(title).containsKey(seasonNumber))
                || ratingMovie.containsKey(title));
    }

    /**
     * @return users by username
     */
    @Override
    public String toString() {
        return username;
    }

    /**
     * username getter
     */
    public String getUsername() {
        return username;
    }

    /**
     * subscription getter
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * history getter
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * favorite movies getter
     */
    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    /**
     * serial rating getter
     */
    public Map<String, Map<Integer, Double>> getRatingSerial() {
        return ratingSerial;
    }

    /**
     * movie rating getter
     */
    public Map<String, Double> getRatingMovie() {
        return ratingMovie;
    }

    /**
     * number of ratings getter
     */
    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    /**
     * number of ratings setter
     */
    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
