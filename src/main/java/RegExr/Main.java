package RegExr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern TAG_REGEX = Pattern.compile("<td class=\"druzyna\">(.+?)</td>", Pattern.DOTALL);
    private static final Integer numberOfTeams = 16;

    public static void main(String[] args) throws IOException {
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL("https://www.plk.pl/tabele.html").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = null;
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter("index.html")) {
            out.println(content);
        }

        //System.out.println(Arrays.toString(getTagValues(content).toArray()));


        final List<String> teams = getTagValues(content);

        for(int counter=0; counter < numberOfTeams; counter++ ){
            System.out.println(counter+1 + "." + teams.get(counter));
        }


        Document doc = Jsoup.connect("https://www.plk.pl/terminarz-i-wyniki.html").get();
        try (PrintWriter out = new PrintWriter("terminarz_i_wyniki.html")) {
            out.println(doc);
        }
        //Elements masthead = doc.select("meta[itemprop=\"homeTeam\"] > span[itemprop=\"awayTeam\"]");;
        Elements row = doc.select("tr[itemscope]");
        //Element homeTeam = row.select("span[itemprop=\"homeTeam\"]").first();
        //Element awayTeam = row.select("span[itemprop=\"awayTeam\"]").first();


        //Elements masthead = doc.select("tr[itemtype=\"http://schema.org/SportsEvent\"]");

        String teamFromQuery = "Stelmet Enea BC Zielona Góra";

        for (Element element : row) {
            Element homeTeam = element.select("span[itemprop=\"homeTeam\"]").first();
            if(homeTeam.ownText().equals(teamFromQuery)){
                Element awayTeam = element.select("span[itemprop=\"awayTeam\"]").first();
                Element date;
                Element score;
                if (element.select("div.tv").first() != null){
                    date = element.select("div.tv > div").first();
                } else {
                    date = element.select("div.below-sm").first();
                }

                if (element.select("td.wynik").first() != null){
                    score = element.select("td.wynik > a").first();
                } else {
                    score = null;
                }

                System.out.println(homeTeam.ownText() + " - " + awayTeam.ownText() + "  " + date.ownText() + " " + score.ownText());
            }
        }

    }

    private static List<String> getTagValues(final String str) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            final Pattern longTeamString = Pattern.compile(">(.+?)<", Pattern.DOTALL);
            final Matcher teamMatcher = longTeamString.matcher(matcher.group(1));
            teamMatcher.find();
            tagValues.add(teamMatcher.group(1));
        }
        return tagValues;
    }
}
