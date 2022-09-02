package entertainment.show;

import entertainment.Season;
import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;

public class Serial extends Show {

    private final int numberOfSeasons;

    private final ArrayList<Season> seasons;

    public Serial(final SerialInputData serialInputData) {
        super(serialInputData);
        this.numberOfSeasons = serialInputData.getNumberSeason();
        this.seasons = new ArrayList<>(serialInputData.getSeasons());
    }

    /**
     * @return average ratings for every season of a serial
     */
    public List<Double> avgRatingSeasons() {
        List<Double> avgSeasons = new ArrayList<>();
        Double sum;
        for (int i = 0; i < numberOfSeasons; ++i) {
            if (seasons.get(i).getRatings().size() == 0) {
                avgSeasons.add(0.0);
            } else {
                sum = 0.0;
                for (Double j: seasons.get(i).getRatings()) {
                    sum += j;
                }
                avgSeasons.add(sum / seasons.get(i).getRatings().size());
            }
        }
        return avgSeasons;
    }

    /**
     * @return the average grade of a serial
     */
    @Override
    public Double averageGrade() {
        Double sum = 0.0;

        for (Double seasonAvg: avgRatingSeasons()) {
            sum += seasonAvg;
        }
        return sum / numberOfSeasons;
    }

    /**
     * @return the duration of a serial
     */
    @Override
    public int overAllDuration() {
        int duration = 0;
        for (Season season: seasons) {
            duration += season.getDuration();
        }
        return  duration;
    }

    /**
     * seasons getter
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
