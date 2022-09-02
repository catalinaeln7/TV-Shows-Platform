package user;

import action.Server;
import database.Database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserSort {
    /**
     * sorts users by sort type
     * @param id action id
     * @param sortType type of sort
     * @param number number of users to be displayed
     */
    public static void sortUser(final int id, final String sortType, final int number) {
        List<User> users;
        switch (sortType) {
            case "asc" -> users = sortAsc();
            case "desc" -> users = sortDesc();
            default -> throw new IllegalStateException("Unexpected value: " + sortType);
        }
        String message = printUsers(users, number);
        Server.getServer().executeAction(id, message);
    }

    /**
     * ascending sort
     * @return list of users
     */
    public static List<User> sortAsc() {
        List<User> users = new ArrayList<>(Database.getDatabase().getUsers());
        users.sort(Comparator.comparingInt(User::getNumberOfRatings).
                thenComparing(User::getUsername));
        users.removeIf(user -> user.getNumberOfRatings() == 0);
        return users;
    }

    /**
     * descending sort
     * @return list of users
     */
    public static List<User> sortDesc() {
        List<User> users = sortAsc();
        Collections.reverse(users);
        return users;
    }

    /**
     * print most active users
     * @param users list of users
     * @param number numbered of users to be displayed
     * @return message for request
     */
    public static String printUsers(final List<User> users, final int number) {
        if (number == 0) {
            return "Query result: " + users;
        } else {
            return "Query result: " + users.subList(0, Math.min(number, users.size()));
        }
    }
}
