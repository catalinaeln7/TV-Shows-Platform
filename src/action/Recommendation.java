package action;

import database.Database;
import entertainment.show.Show;
import entertainment.show.ShowSearch;
import fileio.ActionInputData;
import user.User;
import user.UserMessage;

import java.util.List;

public class Recommendation extends Action {
    public Recommendation(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * calls functions for every type of recommendation
     */
    public void executeRecommendation() {
        Show show = null;
        List<Show> shows = null;
        String typePrintString = printType(type);

        switch (checkSubscription()) {
            case "BASIC" -> {
                switch (type) {
                    case "standard" -> show = ShowSearch.standardRecommendation(username);
                    case "best_unseen" -> show = ShowSearch.bestUnseenRecommendation(username);
                    case "popular", "favorite", "search" -> {
                        UserMessage.couldNotApply(actionId, typePrintString);
                        return;
                    }
                    default -> { }
                }
            }
            case "PREMIUM" -> {
                switch (type) {
                    case "standard" -> show = ShowSearch.standardRecommendation(username);
                    case "best_unseen" -> show = ShowSearch.bestUnseenRecommendation(username);
                    case "popular" -> show = ShowSearch.popularRecommendation(username);
                    case "favorite" -> show = ShowSearch.favoriteRecommendation(username);
                    case "search" -> shows = ShowSearch.searchRecommendation(username, genre);
                    default -> { }
                }
            }
            default -> { }
        }
        if (!type.equals("search")) {
            if (show != null) {
                UserMessage.doneSingleRecommendation(actionId, typePrintString, show);
            } else {
                UserMessage.couldNotApply(actionId, typePrintString);
            }
        } else {
            assert shows != null;
            if (shows.size() != 0) {
                UserMessage.doneSearchRecommendation(actionId, shows);
            } else {
                UserMessage.couldNotApply(actionId, typePrintString);
            }
        }
    }

    /**
     * checks a user's subscription
     * @return type of subscription
     */
    public String checkSubscription() {
        User user = Database.getDatabase().getUserByName(username);
        assert user != null;
        return user.getSubscriptionType();
    }

    /**
     * @param type type of recommendation
     * @return customised string for output message
     */
    public String printType(final String type) {
        switch (type) {
            case "standard" -> {
                return "Standard";
            }
            case "best_unseen" -> {
                return "BestRatedUnseen";
            }
            case "popular" -> {
                return "Popular";
            }
            case "favorite" -> {
                return "Favorite";
            }
            case "search" -> {
                return "Search";
            }
            default -> { }
        }
        return null;
    }
}
