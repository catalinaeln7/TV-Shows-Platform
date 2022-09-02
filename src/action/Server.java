package action;

import database.Database;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.IOException;


public class Server {
    private Writer fileWriter;
    private JSONArray arrayResult;

    private static Server instance = null;

    public Server() { }

    /**
     * returns the program's server - singleton
     * @return server's instance
     */
    public static Server getServer() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    /**
     * the program entry point
     * @param fileWriterName output file
     * @param arrayResultString output string
     * @param input input
     */
     public void entryPoint(final Writer fileWriterName,
                            final JSONArray arrayResultString, final Input input) {
         this.fileWriter = fileWriterName;
         this.arrayResult = arrayResultString;
         Database.getDatabase().loadData(input);
         ActionHandler.executeActions(input.getCommands());
     }

    /**
     * generates the final output of the test
     * @param id action id
     * @param message output message for action
     */
     public void executeAction(final int id, final String message) {
         try {
             arrayResult.add(fileWriter.writeFile(id, null, message));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
}
