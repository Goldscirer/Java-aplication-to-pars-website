package RegExr;

public class Application {

    public static void main(String[] args) {
        boolean runApplication = true;

        while (runApplication) {
            try {
                ConsoleHandler.printPLKTeamsToConsole();
                Integer teamNumber = ConsoleHandler.getNumberFromConsole();
                ConsoleHandler.printPlaceMenu();
                Integer matchPlaceNumber = ConsoleHandler.getNumberFromConsole();
                CompetitionSchedule.getTeamSchedule(teamNumber, matchPlaceNumber);
                ConsoleHandler.waitForUserActivity();
            } catch(Exception exception) {
                runApplication = false;
                System.out.println("Please check your internet connection!");
            }
        }
    }
}
