package actor;

import database.Database;

import java.util.ArrayList;
import java.util.List;

public class ActorSearch {
    /**
     * for query awards
     * @param awards for searching through actors' awards
     * @return list of actors that have all the given awards
     */
    public static List<Actor> getAwardedActors(final List<String> awards) {
        List<Actor> actors = new ArrayList<>();
        int number;
        for (Actor actor: Database.getDatabase().getActors()) {
            number = 0;
            for (String award: awards) {
                if (actor.getAwards().containsKey(ActorsAwards.valueOf(award))) {
                    number++;
                }
            }
            if (number == awards.size()) {
                actors.add(actor);
            }
        }
        return actors;
    }

    /**
     * for query filtered description
     * @param filters for searching in actors' description
     * @return list of actors that respect all the filters
     */
    public static List<Actor> getFilteredDescriptionActors(final List<List<String>> filters) {
        List<Actor> actors = new ArrayList<>();
        int number;

        for (Actor actor: Database.getDatabase().getActors()) {
            number = 0;
            String[] descriptionWords = actor.getCareerDescription().split("\\W+");
            if (filters.get(2).get(0) != null) {
                for (int j = 0; j < filters.get(2).size(); ++j) {
                    for (String word: descriptionWords) {
                        if (filters.get(2).get(j).equalsIgnoreCase(word)) {
                            number++;
                            break;
                        }
                    }
                }
            }
            if (number == filters.get(2).size()) {
                actors.add(actor);
            }
        }
        return actors;
    }
}
