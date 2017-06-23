package Clases;

import Handlers.UserHandler;
import spark.Spark;

import java.util.prefs.Preferences;

import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Preferences userPrefs = Preferences.userRoot();
        userPrefs.putBoolean("first_run", true);
        Boolean isFirstRun = userPrefs.getBoolean("first_run", true);

        if (isFirstRun) {
                 System.out.println("running for the first time");
                  User firstUser = new User("admin", "admin", "admin", true, true);
                  UserHandler userHandler = UserHandler.getInstance();
                  userHandler.insertIntoDatabase(firstUser);
            userPrefs.putBoolean("first_run", false);
        }

        Spark.staticFileLocation("/public");
        enableDebugScreen();
        new manejadorTemplate().startApp();
    }
}
