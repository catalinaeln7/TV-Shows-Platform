package action;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.List;

public class Action {
    protected final int actionId;

    protected final String actionType;

    protected final String type;

    protected final String username;

    protected final String objectType;

    protected final String sortType;

    protected final String criteria;

    protected final String title;

    protected final String genre;

    protected final int number;

    protected final double grade;

    protected final int seasonNumber;

    protected final List<List<String>> filters;

    public Action(final ActionInputData actionInputData) {
        this.actionId = actionInputData.getActionId();
        this.actionType = actionInputData.getActionType();
        this.type = actionInputData.getType();
        this.username = actionInputData.getUsername();
        this.objectType = actionInputData.getObjectType();
        this.sortType = actionInputData.getSortType();
        this.criteria = actionInputData.getCriteria();
        this.title = actionInputData.getTitle();
        this.genre = actionInputData.getGenre();
        this.number = actionInputData.getNumber();
        this.grade = actionInputData.getGrade();
        this.seasonNumber = actionInputData.getSeasonNumber();
        this.filters  = new ArrayList<>(actionInputData.getFilters());
    }

    /**
     * action id getter
     */
    public int getActionId() {
        return actionId;
    }

    /**
     * action type getter
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * type getter
     */
    public String getType() {
        return type;
    }

    /**
     * username getter
     */
    public String getUsername() {
        return username;
    }

    /**
     * criteria getter
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * title getter
     */
    public String getTitle() {
        return title;
    }

    /**
     * genre getter
     */
    public String getGenre() {
        return genre;
    }

    /**
     * number getter
     */
    public int getNumber() {
        return number;
    }

    /**
     * grade getter
     */
    public double getGrade() {
        return grade;
    }

    /**
     * filters getter
     */
    public List<List<String>> getFilters() {
        return filters;
    }
}
