import java.net.*;
import java.io.*;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            URL fred = new URL("https://www.espn.com/fantasy/");
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                int startFrom = 0;
                while (true) {
                    int hrefIndex = line.indexOf("href=", startFrom);
                    if (hrefIndex < 0 || hrefIndex + 5 >= line.length()) {
                        break;
                    }

                    char quote = line.charAt(hrefIndex + 5);
                    if (quote != '"' && quote != '\'') {
                        startFrom = hrefIndex + 5;
                        continue;
                    }

                    int start = hrefIndex + 6;
                    int end = line.indexOf(quote, start);
                    if (end < 0) {
                        break;
                    }

                    String link = line.substring(start, end);
                    link = link.replace("&amp;", "&");

                    if (link.startsWith("//")) {
                        link = "https:" + link;
                    } else if (link.startsWith("/")) {
                        link = "https://" + fred.getHost() + link;
                    }

                    if (link.startsWith("http")) {
                        System.out.println(link);
                    }

                    startFrom = end + 1;

                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
            //URLConnection urlc = fred.openConnection();
            // urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            //BufferedReader reader = new BufferedReader(
            // new InputStreamReader(urlc.getInputStream()));
            //String line;
            //while ((line = reader.readLine()) != null) {
            // if (line.contains("milton")) {
            //System.out.println("where = " + line.indexOf(".edu"));
            //System.out.println(line);
        }
    }
}