package RegExr;

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

    public static void main(String[] args) throws IOException {
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL("https://www.plk.pl/tabele.html").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter("index.html")) {
            out.println(content);
        }

        System.out.println(Arrays.toString(getTagValues(content).toArray()));

        final Pattern pattern = Pattern.compile("<td class=\"druzyna\">(.+?)</td>", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(content);
        matcher.find();
        final Pattern longTeamString = Pattern.compile(">(.+?)<", Pattern.DOTALL);
        final Matcher teamMatcher = longTeamString.matcher(matcher.group(1));
        teamMatcher.find();
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
