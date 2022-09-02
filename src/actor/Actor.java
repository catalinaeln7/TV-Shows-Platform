package actor;

import database.Database;
import entertainment.show.Show;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Actor {
    private final String name;

    private final String careerDescription;

    private final ArrayList<String> filmography;

    private final Map<ActorsAwards, Integer> awards;

    public Actor(final ActorInputData actorInputData) {
        this.name = actorInputData.getName();
        this.careerDescription = actorInputData.getCareerDescription();
        this.filmography = new ArrayList<>(actorInputData.getFilmography());
        this.awards = new HashMap<>(actorInputData.getAwards());
    }

    /**
     * @return the average rating of videos with actor
     */
    public Double getRating() {
        Double sum = 0.0;
        int nrVideos = 0;
        for (String video: filmography) {
            Show show = Database.getDatabase().getShowByTitle(video);
            if (show != null) {
                if (show.averageGrade() != 0.0) {
                    sum += show.averageGrade();
                    nrVideos++;
                }
            }
        }
        if (nrVideos == 0) {
            return 0.0;
        }
        return sum / nrVideos;
    }

    /**
     * @return number of awards of an actor
     */
    public int nrOfAwards() {
        int nr = 0;
        for (ActorsAwards actorsAwards: awards.keySet()) {
            nr += awards.get(actorsAwards);
        }
        return nr;
    }

    /**
     * @return names of actors
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * name getter
     */
    public String getName() {
        return name;
    }

    /**
     * carrer description getter
     */
    public String getCareerDescription() {
        return careerDescription;
    }

    /**
     * filmography getter
     */
    public ArrayList<String> getFilmography() {
        return filmography;
    }

    /**
     * awards getter
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }
}
