package RegExr;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

class CompetitionSchedule {

    private static final String scheduleAndScoreURL = "https://www.plk.pl/terminarz-i-wyniki.html";
    private static final String awayTeamTag = "span[itemprop=\"awayTeam\"]";
    private static final String homeTeamTag = "span[itemprop=\"homeTeam\"]";
    private static final String tvTag = "div.tv";
    private static final String contentChildTVTag = "div.tv > div";
    private static final String tvAndDateTag = "div.below-sm";
    private static final String scoreTag = "td.wynik";
    private static final String contentOfScoreTag = "td.wynik > a";
    private static final String scheduleTable = "tr[itemscope]";

    static void getTeamSchedule(Integer teamNumber, Integer mPlace) throws IOException {
        Document scheduleAndScoreWebContent = FetchWebContent.getContentFromURLbyJSOUP(scheduleAndScoreURL);
        Elements row = scheduleAndScoreWebContent.select(scheduleTable);
        String teamFromQuery = PLKTeams.getPLKTeams().get(teamNumber - 1);
        MatchPlace matchPlace = mPlace == 1 ? MatchPlace.HOME : MatchPlace.AWAY;
        System.out.println(matchPlace == MatchPlace.HOME);
        if (matchPlace == MatchPlace.HOME) {
            getHomeMatches(row, teamFromQuery, matchPlace);
        } else {
            getAwayMatches(row, teamFromQuery, matchPlace);
        }
    }

    private static void getHomeMatches(Elements row, String teamFromQuery, MatchPlace place) {
        for (Element element : row) {
            Element homeTeam = element.select(homeTeamTag).first();
            findScheduleOfTheTeam(homeTeam, teamFromQuery, element, awayTeamTag, place);
        }
    }

    private static void getAwayMatches(Elements row, String teamFromQuery, MatchPlace place) {
        for (Element element : row) {
            Element homeTeam = element.select(awayTeamTag).first();
            findScheduleOfTheTeam(homeTeam, teamFromQuery, element, homeTeamTag, place);
        }
    }

    private static void findScheduleOfTheTeam(Element homeTeam, String teamFromQuery, Element element,
                                              String awayTeamTag, MatchPlace place) {
        if (homeTeam.ownText().equals(teamFromQuery)) {
            Element awayTeam = element.select(awayTeamTag).first();
            Element date = findMatchDateOfTheTeam(element);
            Element score = findMatchScoreOfTheTeam(element);
            Match match = new Match(homeTeam.ownText(), awayTeam.ownText(), date.ownText(), score.ownText());
            ConsoleHandler.printScheduleToConsole(match, place);
        }
    }

    private static Element findMatchDateOfTheTeam(Element element) {
        Element date;
        if (element.select(tvTag).first() != null) {
            date = element.select(contentChildTVTag).first();
        } else {
            date = element.select(tvAndDateTag).first();
        }
        return date;
    }

    private static Element findMatchScoreOfTheTeam(Element element) {
        Element score;
        if (element.select(scoreTag).first() != null) {
            score = element.select(contentOfScoreTag).first();
        } else {
            score = null;
        }
        return score;
    }
}
