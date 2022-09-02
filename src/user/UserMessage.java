package user;

import action.Action;
import action.Server;
import entertainment.show.Show;

import java.util.List;

public class UserMessage {

    /**
     * successfully applied recommendation
     * @param actionId action id
     * @param typePrintString custom print
     * @param show show that should be printed
     */
    public static void doneSingleRecommendation(final int actionId,
                                                final String typePrintString, final Show show) {
        Server.getServer().executeAction(actionId, typePrintString
                + "Recommendation result: " + show.getTitle());
    }

    /**
     * successfully applied search recommendation
     * @param actionId action id
     * @param shows list of shows
     */
    public static void doneSearchRecommendation(final int actionId, final List<Show> shows) {
        Server.getServer().executeAction(actionId, "SearchRecommendation result: " + shows);
    }

    /**
     * could not apply recommendation
     * @param actionId action id
     * @param typePrintString custom print
     */
    public static void couldNotApply(final int actionId, final String typePrintString) {
        Server.getServer().
                executeAction(actionId, typePrintString + "Recommendation cannot be applied!");
    }

    /**
     * successfully adds video as favorite
     * @param action action with all attributes
     */
    public static void addsFav(final Action action) {
        Server.getServer().executeAction(action.getActionId(), "success -> "
                + action.getTitle() + " was added as favourite");
    }

    /**
     * already added as favorite
     * @param action action with all attributes
     */
    public static void isAlreadyFav(final Action action) {
        Server.getServer().executeAction(action.getActionId(), "error -> "
                + action.getTitle() + " is already in favourite list");
    }

    /**
     * user viewed video
     * @param action action with all attributes
     * @param user user with all attributes
     */
    public static void addView(final Action action, final User user) {
        Server.getServer().executeAction(action.getActionId(), "success -> "
                + action.getTitle() + " was viewed with total views of "
                + user.getHistory().get(action.getTitle()));
    }

    /**
     * user has not seen video
     * @param action action with all attributes
     */
    public static void hasNotSeenVideo(final Action action) {
        Server.getServer().executeAction(action.getActionId(), "error -> "
                + action.getTitle() + " is not seen");
    }

    /**
     * already rated
     * @param action action with all attributes
     */
    public static void hasRatedVideo(final Action action) {
        Server.getServer().executeAction(action.getActionId(), "error -> "
                + action.getTitle() + " has been already rated");
    }

    /**
     * successfully rated
     * @param action action with all attributes
     * @param user user with all attributes
     */
    public static void ratesVideo(final Action action, final User user) {
        Server.getServer().executeAction(action.getActionId(), "success -> "
                + action.getTitle() + " was rated with "
                + action.getGrade() + " by " + user.getUsername());
    }
}
