package RegExr;

import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {
    public static Integer getNumberFromConsole() {
        Scanner inputNumber = new Scanner(System.in);
        return inputNumber.nextInt();
    }

    public static void printPlaceMenu() {
        System.out.println("Please choose place:");
        System.out.println("1. Home");
        System.out.println("2. Away");
    }

    public static void printPLKTeamsToConsole() throws IOException {
        final List<String> teams = PLKTeams.getPLKTeams();

        for (int counter = 0; counter < PLKTeams.getNumberOfTeams(); counter++) {
            System.out.println(counter + 1 + "." + teams.get(counter));
        }
    }

    public static void printScheduleToConsole(Element searchTems, Element enemyTeam, Element date, Element score, Place place) {
        if (place.equals(Place.home)) {
            System.out.println(searchTems.ownText() + " - " + enemyTeam.ownText() + "  " + date.ownText() + " " + score.ownText());
        } else {
            System.out.println(enemyTeam.ownText() + " - " + searchTems.ownText() + "  " + date.ownText() + " " + score.ownText());
        }
    }
}
