package action;

import database.Database;
import entertainment.show.Movie;
import entertainment.show.Serial;
import fileio.ActionInputData;
import user.User;
import user.UserMessage;

import java.util.HashMap;

public class Command extends Action {
    public Command(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * calls functions for every type of command
     */
    public void executeCommand() {
        switch (type) {
            case "favorite" -> favorite();
            case "view" -> view();
            case "rating" -> rating();
            default -> {
            }
        }
    }

    /**
     * adds video to favorite if not added already
     */
    public void favorite() {
        User user = Database.getDatabase().getUserByName(username);
        assert user != null;
        if (user.hasSeen(title)) {
            if (!user.wasAddedAsFavorite(title)) {
                user.getFavoriteMovies().add(title);
                UserMessage.addsFav(this);
            } else {
                UserMessage.isAlreadyFav(this);
            }
        } else {
            UserMessage.hasNotSeenVideo(this);
        }
    }

    /**
     * views video if not seen
     */
    public void view() {
        User user = Database.getDatabase().getUserByName(username);
        assert user != null;
        if (user.hasSeen(title)) {
            user.getHistory().replace(title, user.getHistory().get(title) + 1);
        } else {
            user.getHistory().put(title, 1);
        }
        UserMessage.addView(this, user);
    }

    /**
     * rates video if seen and not rated
     */
    public void rating() {
        User user = Database.getDatabase().getUserByName(username);
        assert user != null;
        if (!user.hasSeen(title)) {
            UserMessage.hasNotSeenVideo(this);
        } else if (user.hasRated(title, seasonNumber)) {
            UserMessage.hasRatedVideo(this);
        } else {
            addRatingsToVideosAndUsers(user);
            UserMessage.ratesVideo(this, user);
        }
    }

    /**
     * stores ratings given by users for every user and video
     * @param user user that rated the video
     */
    public void addRatingsToVideosAndUsers(final User user) {
        if (Database.getDatabase().isMovie(title)) {
            Movie movie = Database.getDatabase().getMovieByTitle(title);
            user.getRatingMovie().put(title, grade);
            assert movie != null;
            movie.getRatingMovie().add(grade);
        } else if (Database.getDatabase().isSerial(title)) {
            Serial serial = Database.getDatabase().getSerialByTitle(title);
            user.getRatingSerial().put(title, new HashMap<>());
            user.getRatingSerial().get(title).put(seasonNumber, grade);
            assert serial != null;
            serial.getSeasons().get(seasonNumber - 1).getRatings().add(grade);
        }
        user.setNumberOfRatings(user.getNumberOfRatings() + 1);
    }
}
