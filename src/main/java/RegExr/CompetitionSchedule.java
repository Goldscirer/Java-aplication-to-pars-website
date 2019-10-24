package RegExr;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CompetitionSchedule {

    private static final String scheduleAndScoreURL = "https://www.plk.pl/terminarz-i-wyniki.html";

    protected static void getTeamSchedule(Integer teamNumber, Integer matchPlace) throws IOException {
        Document scheduleAndScoreWebContent = FetchWebContent.getContentFromURLbyJSOUP(scheduleAndScoreURL);
        Elements row = scheduleAndScoreWebContent.select("tr[itemscope]");
        String teamFromQuery = PLKTeams.getPLKTeams().get(teamNumber - 1);

        if (matchPlace == 1) {
            getHomeMatches(row, teamFromQuery);
        } else {
            getAwayMatches(row, teamFromQuery);
        }
    }

    private static void getHomeMatches(Elements row, String teamFromQuery) {
        for (Element element : row) {
            Element homeTeam = element.select("span[itemprop=\"homeTeam\"]").first();
            if (homeTeam.ownText().equals(teamFromQuery)) {
                Element awayTeam = element.select("span[itemprop=\"awayTeam\"]").first();
                Element date;
                Element score;
                if (element.select("div.tv").first() != null) {
                    date = element.select("div.tv > div").first();
                } else {
                    date = element.select("div.below-sm").first();
                }

                if (element.select("td.wynik").first() != null) {
                    score = element.select("td.wynik > a").first();
                } else {
                    score = null;
                }
                ConsoleHandler.printScheduleToConsole(homeTeam, awayTeam, date, score, Place.home);
            }
        }
    }

    private static void getAwayMatches(Elements row, String teamFromQuery) {
        for (Element element : row) {
            Element homeTeam = element.select("span[itemprop=\"homeTeam\"]").first();
            if (homeTeam.ownText().equals(teamFromQuery)) {
                Element awayTeam = element.select("span[itemprop=\"awayTeam\"]").first();
                Element date;
                Element score;
                if (element.select("div.tv").first() != null) {
                    date = element.select("div.tv > div").first();
                } else {
                    date = element.select("div.below-sm").first();
                }

                if (element.select("td.wynik").first() != null) {
                    score = element.select("td.wynik > a").first();
                } else {
                    score = null;
                }
                ConsoleHandler.printScheduleToConsole(homeTeam, awayTeam, date, score, Place.away);
            }
        }
    }
}
