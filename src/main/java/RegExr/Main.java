package RegExr;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
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
        System.out.println(content);
        final Pattern pattern = Pattern.compile("html\">(.+?)</a>", Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(content);
        matcher.find();
        System.out.println(matcher.group(1));


        //System.out.println("TO jest moja zajebista srona" + content);
    }
}
