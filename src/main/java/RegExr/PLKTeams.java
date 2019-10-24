package RegExr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PLKTeams {

    private static final String teamListURL = "https://www.plk.pl/tabele.html";
    private static final Pattern TAG_REGEX = Pattern.compile("<td class=\"druzyna\">(.+?)</td>", Pattern.DOTALL);
    private static final Integer numberOfTeams = 16;

    protected static List<String> getPLKTeams() throws IOException {
        String webContent = FetchWebContent.getContentFromURL(teamListURL);
        return convertWebContentToTeamList(webContent);
    }

    private static List<String> convertWebContentToTeamList(final String webContent) {
        final List<String> tagValues = new ArrayList<>();
        final Matcher matcher = TAG_REGEX.matcher(webContent);
        while (matcher.find()) {
            final Pattern longTeamString = Pattern.compile(">(.+?)<", Pattern.DOTALL);
            final Matcher teamMatcher = longTeamString.matcher(matcher.group(1));
            teamMatcher.find();
            tagValues.add(teamMatcher.group(1));
        }
        return tagValues;
    }

    protected static Integer getNumberOfTeams() {
        return numberOfTeams;
    }
}
