package action;

import actor.ActorSort;
import entertainment.show.ShowSort;
import fileio.ActionInputData;
import user.UserSort;

public class Query extends Action {
    public Query(final ActionInputData actionInputData) {
        super(actionInputData);
    }

    /**
     * calls functions for every type of query
     */
    public void executeQuery() {
        switch (objectType) {
            case "actors" -> ActorSort.sortActors(actionId, criteria, sortType, number, filters);
            case "shows", "movies" -> ShowSort.sortShow(actionId, criteria, sortType,
                                                        number, filters, objectType);
            case "users" -> UserSort.sortUser(actionId, sortType, number);
            default -> { }
        }
    }
}
