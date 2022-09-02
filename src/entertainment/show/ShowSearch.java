package entertainment.show;

import database.Database;
import user.User;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;

public class ShowSearch {

    /**
     * @param username name of user
     * @return first show from database not seen by user
     */
    public static Show standardRecommendation(final String username) {
        User user = Database.getDatabase().getUserByName(username);
        List<Show> shows = Database.getDatabase().allShows();

        for (Show show : shows) {
            assert user != null;
            if (!user.getHistory().containsKey(show.title)) {
                return show;
            }
        }
        return null;
    }

    /**
     * @param username name of user
     * @return show with best rating not seen by user
     */
    public static Show bestUnseenRecommendation(final String username) {
        User user = Database.getDatabase().getUserByName(username);
        List<Show> shows = new ArrayList<>();
        List<Movie> movies = new ArrayList<>(Database.getDatabase().getMovies());
        List<Serial> serials = new ArrayList<>(Database.getDatabase().getSerials());

        movies.sort(Comparator.comparingDouble(Show::averageGrade).reversed());
        serials.sort(Comparator.comparingDouble(Show::averageGrade).reversed());

        shows.addAll(movies);
        shows.addAll(serials);
        shows.sort(Comparator.comparingDouble(Show::averageGrade).reversed());

        for (Show show: shows) {
            assert user != null;
            if (!user.getHistory().containsKey(show.title)) {
                return show;
            }
        }
        return null;
    }

    /**
     * @param username name of user
     * @return the most viewed show not seen by user
     */
    public static Show popularRecommendation(final String username) {
        User user = Database.getDatabase().getUserByName(username);
        Map<String, Integer> popularGenre = new HashMap<>();
        List<Show> shows = Database.getDatabase().allShows();

        for (Show show : shows) {
            for (String genre: show.getGenres()) {
                if (!popularGenre.containsKey(genre)) {
                    popularGenre.put(genre, show.nrViews());
                } else {
                    popularGenre.put(genre, popularGenre.get(genre) + show.nrViews());
                }
            }
        }

        popularGenre = sortMapByValue(popularGenre);

        List<Show> allShows = Database.getDatabase().allShows();

        for (String genre : popularGenre.keySet()) {
            for (Show show : allShows) {
                if (show.getGenres().contains(genre)) {
                    assert user != null;
                    if (!user.getHistory().containsKey(show.title)) {
                        return show;
                    }
                }
            }
        }

        return null;
    }

    /**
     * @param username name of the user
     * @return show that has been added in user's favorite list the most
     */
    public static Show favoriteRecommendation(final String username) {
        User user = Database.getDatabase().getUserByName(username);
        List<Show> shows = new ArrayList<>();
        List<Movie> movies = new ArrayList<>(Database.getDatabase().getMovies());
        List<Serial> serials = new ArrayList<>(Database.getDatabase().getSerials());

        movies.sort(Comparator.comparingDouble(Show::nrOfAddedAsFavorite).reversed());
        serials.sort(Comparator.comparingDouble(Show::nrOfAddedAsFavorite).reversed());

        shows.addAll(movies);
        shows.addAll(serials);
        shows.sort(Comparator.comparingInt(Show::nrOfAddedAsFavorite).reversed());
        shows.removeIf(show -> show.nrOfAddedAsFavorite() == 0);

        for (Show show : shows) {
            assert user != null;
            if (!user.getHistory().containsKey(show.title)) {
                return show;
            }
        }
        return null;
    }

    /**
     * @param username name of the user
     * @param genre genre to search by
     * @return a list of shows from that genre not seen by user
     */
    public static List<Show> searchRecommendation(final String username,
                                                  final String genre) {
        User user = Database.getDatabase().getUserByName(username);
        List<Show> searchedShowsByGenre = new ArrayList<>();
        List<Show> shows = Database.getDatabase().allShows();

        for (Show show : shows) {
            assert user != null;
            if (!user.getHistory().containsKey(show.title)) {
                if (show.getGenres().contains(genre)) {
                    searchedShowsByGenre.add(show);
                }
            }
        }

        searchedShowsByGenre.sort(Comparator.comparing(Show::getTitle));

        return searchedShowsByGenre;
    }

    /**
     * @param unsortedMap the unsorted map
     * @return sorted map by value
     */
    public static Map<String, Integer>
    sortMapByValue(final Map<String, Integer> unsortedMap) {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    /**
     * @param filters to search by
     * @param shows shows to search through
     * @return filtered shows
     */
    public static List<Show> getFilteredShows(final List<List<String>> filters,
                                              final List<Show> shows) {
        List<Show> filteredShows = new ArrayList<>();
        int number, nrFilters = 0;

        if (filters.get(0).get(0) != null) {
            nrFilters++;
        }
        if (filters.get(1).get(0) != null) {
            nrFilters++;
        }

        for (Show show: shows) {
            number = 0;
            if (filters.get(0).get(0) != null) {
                if (filters.get(0).get(0).equals(String.valueOf(show.getYear()))) {
                    number++;
                }
            }
            if (filters.get(1).get(0) != null) {
                for (String genre: show.getGenres()) {
                    if (genre.equalsIgnoreCase(filters.get(1).get(0))) {
                        number++;
                        break;
                    }
                }
            }
            if (number == nrFilters) {
                filteredShows.add(show);
            }
        }
        return filteredShows;
    }
}
