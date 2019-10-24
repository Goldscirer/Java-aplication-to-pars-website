package RegExr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class FetchWebContent {

    public static String getContentFromURL(String webUrl) throws IOException {
        String content = null;
        URLConnection connection = null;
        try {
            connection = new URL(webUrl).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return content;
    }

    public static Document getContentFromURLbyJSOUP(String webUrl) throws IOException {
        return Jsoup.connect(webUrl).get();

    }

    public static void saveWebToFIle(String webUrl) throws IOException {
        try (PrintWriter out = new PrintWriter("index.html")) {
            out.println(webUrl);
        }
    }
}
