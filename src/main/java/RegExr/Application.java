package RegExr;

import java.io.*;

public class Application {

    public static void main(String[] args) throws IOException {

        while (true) {
            ConsoleHandler.printPLKTeamsToConsole();
            Integer teamNumber = ConsoleHandler.getNumberFromConsole();
            ConsoleHandler.printPlaceMenu();
            Integer matchPlaceNumber = ConsoleHandler.getNumberFromConsole();
            CompetitionSchedule.getTeamSchedule(teamNumber, matchPlaceNumber);
            Integer stopWhenEnd = ConsoleHandler.getNumberFromConsole();
        }
    }
}
