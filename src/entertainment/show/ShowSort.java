package entertainment.show;

import action.Server;
import database.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowSort {

    /**
     * @param id action id
     * @param criteria type of query for shows
     * @param sortType ascending or descending
     * @param number of shows that should be displayed
     * @param filters to be searched by
     * @param objectType movie or serial
     */
    public static void sortShow(final int id, final String criteria, final String sortType,
                                final int number, final List<List<String>> filters,
                                final String objectType) {
        List<Show> shows = filtered(objectType, filters);
        switch (criteria) {
            case "ratings" -> rating(shows);
            case "favorite" -> favorite(shows);
            case "longest" -> longest(shows);
            case "most_viewed" -> mostViewed(shows);
            default -> throw new IllegalStateException("Unexpected value: " + criteria);
        }

        if (sortType.equals("desc")) {
            sortDesc(shows);
        }

        String message = printShows(shows, number);
        Server.getServer().executeAction(id, message);
    }

    /**
     * @param objectType movie or serial
     * @param filters to be searched by
     * @return list of filtered shows
     */
    public static List<Show> filtered(final String objectType, final List<List<String>> filters) {
        List<Show> shows = new ArrayList<>();

        if (objectType.equals("movies")) {
            shows.addAll(new ArrayList<>(Database.getDatabase().getMovies()));
        } else if (objectType.equals("shows")) {
            shows.addAll(new ArrayList<>(Database.getDatabase().getSerials()));
        }
        shows = ShowSearch.getFilteredShows(filters, shows);
        return shows;
    }

    /**
     * @param shows selected shows
     * @return list with best rated shows
     */
    public static List<Show> rating(final List<Show> shows) {
        shows.sort(Comparator.comparingDouble(Show::averageGrade).
                thenComparing(Show::getTitle));
        shows.removeIf(show -> show.averageGrade() == 0.0);
        return shows;
    }

    /**
     * @param shows selected shows
     * @return list with most favorite shows
     */
    public static List<Show> favorite(final List<Show> shows) {
        shows.sort(Comparator.comparingDouble(Show::nrOfAddedAsFavorite).
                thenComparing(Show::getTitle));
        shows.removeIf(show -> show.nrOfAddedAsFavorite() == 0.0);
        return shows;
    }

    /**
     * @param shows selected shows
     * @return list with the longest shows
     */
    public static List<Show> longest(final List<Show> shows) {
        shows.sort(Comparator.comparingDouble(Show::overAllDuration).
                thenComparing(Show::getTitle));
        shows.removeIf(show -> show.overAllDuration() == 0.0);
        return shows;
    }

    /**
     * @param shows selected shows
     */
    public static void mostViewed(final List<Show> shows) {
        shows.sort(Comparator.comparingDouble(Show::nrViews).
                thenComparing(Show::getTitle));
        shows.removeIf(show -> show.nrViews() == 0.0);
    }

    /**
     * sort shows descending
     * @param shows shows that should be sorted
     */
    public static void sortDesc(final List<Show> shows) {
        if (shows != null) {
            Collections.reverse(shows);
        }
    }

    /**
     * @param shows shows that should be displayed
     * @param number number of shows that should be displayed
     * @return message for request
     */
    public static String printShows(final List<Show> shows, final int number) {
        if (shows == null) {
            return "Query result: []";
        } else if (number == 0) {
            return "Query result: " + shows;
        } else {
            return "Query result: " + shows.subList(0, Math.min(number, shows.size()));
        }
    }
}
