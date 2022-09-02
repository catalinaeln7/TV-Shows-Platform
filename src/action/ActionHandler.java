package action;

import fileio.ActionInputData;

import java.util.List;

class ActionHandler {

    /**
     * calls functions for every type of action
     * @param inputData input information
     */
    public static void executeActions(final List<ActionInputData> inputData) {
        for (ActionInputData actionInputData : inputData) {
            switch (actionInputData.getActionType()) {
                case "command" -> new Command(actionInputData).executeCommand();
                case "query" -> new Query(actionInputData).executeQuery();
                case "recommendation"
                        -> new Recommendation(actionInputData).executeRecommendation();
                default -> {
                }
            }
        }
    }
}

