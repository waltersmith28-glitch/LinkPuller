import java.net.*;
import java.io.*;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();
    }
    public HtmlRead(){

        try{
            System.out.println();
            URL fred = new URL("https://en.wikipedia.org/wiki/LeBron_James");
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if(line.contains("https")){
                    int start = line.indexOf("https");
                    int end = line.indexOf('"', start);
                    System.out.println(line.substring(start,end - 1));
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
        } catch(Exception ex){
            System.out.println(ex);
        }
    }



}