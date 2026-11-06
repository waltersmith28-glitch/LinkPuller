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
                if (line.contains("https")) {
                    //System.out.println(line);
                    int start = line.indexOf("https");

                    int end = line.indexOf('"', start);
                    if (end < 0) {
                        int single = line.indexOf("'", start);
                        System.out.println(line.substring(start, single));
                    }
                    else{
                    System.out.println(line.substring(start, end));
                    }
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
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


}