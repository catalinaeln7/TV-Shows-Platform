package database;

import actor.Actor;
import entertainment.show.Movie;
import entertainment.show.Serial;
import entertainment.show.Show;
import fileio.ActorInputData;
import fileio.SerialInputData;
import fileio.MovieInputData;
import fileio.Input;
import fileio.UserInputData;
import user.User;

import java.util.ArrayList;
import java.util.List;

public final class Database {
    private List<Actor> actors;

    private List<User> users;

    private List<Movie> movies;

    private List<Serial> serials;

    private static Database instance = null;

    private Database() { }

    /**
     * returns the program's database - singleton
     * @return database's instance
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * loads all the data in database
     * @param input input information
     */
    public void loadData(final Input input) {
        this.actors = loadActors(input.getActors());
        this.users = loadUsers(input.getUsers());
        this.movies = loadMovies(input.getMovies());
        this.serials = loadSerials(input.getSerials());
    }

    /**
     * loads actors in database
     * @param inputData input information
     * @return list of actors to be loaded in database
     */
    public List<Actor> loadActors(final List<ActorInputData> inputData) {
        List<Actor> myActors = new ArrayList<>();
        for (ActorInputData actorInputData: inputData) {
            Actor actor = new Actor(actorInputData);
            myActors.add(actor);
        }
        return myActors;
    }

    /**
     * loads users in database
     * @param inputData input information
     * @return list of users to be loaded in database
     */
    public List<User> loadUsers(final List<UserInputData> inputData) {
        List<User> myUsers = new ArrayList<>();
        for (UserInputData userInputData: inputData) {
            User user = new User(userInputData);
            myUsers.add(user);
        }
        return myUsers;
    }

    /**
     * loads movies in database
     * @param inputData input information
     * @return list of movies to be loaded in db
     */
    public List<Movie> loadMovies(final List<MovieInputData> inputData) {
        List<Movie> myMovies = new ArrayList<>();
        for (MovieInputData movieInputData: inputData) {
            Movie movie = new Movie(movieInputData);
            myMovies.add(movie);
        }
        return myMovies;
    }

    /**
     * loads serials in database
     * @param inputData input information
     * @return list of serials to be loaded in db
     */
    public List<Serial> loadSerials(final List<SerialInputData> inputData) {
        List<Serial> mySerials = new ArrayList<>();
        for (SerialInputData serialInputData: inputData) {
            Serial serial = new Serial(serialInputData);
            mySerials.add(serial);
        }
        return mySerials;
    }

    /**
     * @param username name of the user
     * @return the user with that username
     */
    public User getUserByName(final String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @param title title of the movie
     * @return the movie with that title
     */
    public Movie getMovieByTitle(final String title) {
        for (Movie movie: movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * @param title title of the serial
     * @return serial with that title
     */
    public Serial getSerialByTitle(final String title) {
        for (Serial serial: serials) {
            if (serial.getTitle().equals(title)) {
                return serial;
            }
        }
        return null;
    }

    /**
     * @param title title of the video
     * @return the show with that title
     */
    public Show getShowByTitle(final String title) {
        if (isMovie(title)) {
            return getMovieByTitle(title);
        } else if (isSerial(title)) {
            return getSerialByTitle(title);
        }
        return null;
    }

    /**
     * @param title title of the video
     * @return true if it is movie
     */
    public boolean isMovie(final String title) {
        for (Movie movie: movies) {
            if (movie.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param title title of the video
     * @return true if it is serial
     */
    public boolean isSerial(final String title) {
        for (Serial serial: serials) {
            if (serial.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a list with all shows
     */
    public List<Show> allShows() {
        List<Show> shows = new ArrayList<>();
        shows.addAll(Database.getDatabase().getMovies());
        shows.addAll(Database.getDatabase().getSerials());
        return shows;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Serial> getSerials() {
        return serials;
    }
}
