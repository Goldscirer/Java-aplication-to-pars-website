package RegExr;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class ConsoleHandler {
    static Integer getNumberFromConsole() {
        Scanner inputNumber = new Scanner(System.in);
        return inputNumber.nextInt();
    }

    static void printPlaceMenu() {
        System.out.println("Please choose place:");
        System.out.println("1. Home");
        System.out.println("2. Away");
    }

    static void printPLKTeamsToConsole() throws IOException {
        final List<String> teams = PLKTeams.getPLKTeams();

        for (int counter = 0; counter < PLKTeams.getNumberOfTeams(); counter++) {
            System.out.println(counter + 1 + "." + teams.get(counter));
        }
    }

    static void printScheduleToConsole(Match match, MatchPlace place) {
        if (place.equals(MatchPlace.HOME)) {
            System.out.println(match.getHomeTeam() + " - " + match.getAwayTeam() + "  "
                    + match.getDate() + " " + match.getScore());
        } else {
            System.out.println(match.getAwayTeam() + " - " + match.getHomeTeam() + "  "
                    + match.getDate() + " " + match.getScore());        }
    }

    static void waitForUserActivity(){
        Scanner s=new Scanner(System.in);
        System.out.println("Press enter to continue.....");
        s.nextLine();
    }
}
