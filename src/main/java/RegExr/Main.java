package RegExr;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL("https://www.plk.pl/terminarz-i-wyniki.html").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        System.out.println(content);
        System.out.println("TO jest moja zajebista srona" + content);
    }
}
