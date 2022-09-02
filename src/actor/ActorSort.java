package actor;

import action.Server;
import database.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActorSort {
    /**
     * calls functions for every criteria
     * @param id action id
     * @param criteria criteria for query
     * @param sortType ascending or descending
     * @param number number of actors that should be displayed
     * @param filters filters to be applied
     */
    public static void sortActors(final int id, final String criteria, final String sortType,
                                  final int number, final List<List<String>> filters) {
        List<Actor> actors;
        var nr = 3;
        switch (criteria) {
            case "average" -> actors = average();
            case "awards" -> actors = awards(filters.get(nr));
            case "filter_description" -> actors = filterDescription(filters);
            default -> throw new IllegalStateException("Unexpected value: " + criteria);
        }

        if (sortType.equals("desc")) {
            sortDesc(actors);
        }

        String message = printActors(actors, number);
        Server.getServer().executeAction(id, message);
    }

    /**
     * @return list of actors ordered ascending by rating average
     */
    public static List<Actor> average() {
        List<Actor> actors = new ArrayList<>(Database.getDatabase().getActors());
        actors.sort(Comparator.comparingDouble(Actor::getRating).thenComparing(Actor::getName));
        actors.removeIf(actor -> actor.getRating() == 0.0);
        return actors;
    }

    /**
     * gets actors by awards
     * @param awards for searching through actors' awards
     * @return list of actors that have all the awards given
     */
    public static List<Actor> awards(final List<String> awards) {
        List<Actor> awardedActors = ActorSearch.getAwardedActors(awards);
        awardedActors.sort(Comparator.comparingDouble(Actor::nrOfAwards).
                thenComparing(Actor::getName));
        return awardedActors;
    }

    /**
     * gets actors by filter
     * @param filters for searching in description
     * @return list of actors that respect the filters
     */
    public static List<Actor> filterDescription(final List<List<String>> filters) {
        List<Actor> actors = ActorSearch.getFilteredDescriptionActors(filters);
        actors.sort(Comparator.comparing(Actor::getName));
        return actors;
    }

    /**
     * descending order of actor
     * @param actors actors given
     */
    public static void sortDesc(final List<Actor> actors) {
        if (actors != null) {
            Collections.reverse(actors);
        }
    }

    /**
     * for query - actors
     * @param actors that respect the query requests
     * @param number number of actors that should be shown
     * @return message for request
     */
    public static String printActors(final List<Actor> actors, final int number) {
        if (actors == null) {
            return "Query result: []";
        } else if (number == 0) {
            return "Query result: " + actors;
        } else {
            return "Query result: " + actors.subList(0, Math.min(number, actors.size()));
        }
    }
}
