import java.net.*;
import java.io.*;
import java.util.Scanner; //nik helped me with this

public class HtmlRead {//Creates an HtmlRead object, which runs the constructor below
    public static void main(String[] args) {
        HtmlRead app = new HtmlRead();
    }

    public HtmlRead() {
        try {
            Scanner scanner = new Scanner(System.in);//creates a Scanner that reads from System.in, which is the keyboard/chatbox

            System.out.println();
            System.out.println("What link would you like to pull links from?");//asks the user to type and press Enter.
            String website = scanner.nextLine().trim();//nextLine() waits for the user to type and press enter, then returns what they typed.
            //trim() removes any spaces at the start or end. The address must include https:// or http://.

            System.out.println("What should the links contain?(press Enter to see all links)"); //asks the user for text that every printed link must contain.
            String filter = scanner.nextLine().trim().toLowerCase();// reads the filter text.
            // toLowerCase() makes it all lowercase so the comparison later on ignores capital letters. If the user just presses Enter, the filter is empty, and every link is printed.
            scanner.close();//closes the scanner

            URL url = new URL(website); //tells the program which website to pull links from.
            URLConnection urlc = url.openConnection(); //Adds a "User-Agent" header that makes the request look like it came from a web browser
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            int count = 0;//keeps track of how many links have been printed.
            String line;
            while ((line = reader.readLine()) != null) {// Reads the next line of HTML into "line". readLine() returns null when the page is
                // finished, which ends this loop. Everything inside runs once for every line of the page.
                int startFrom = 0;
                while (true){
                    int hrefIndex = line.indexOf("href=", startFrom);

                    if(hrefIndex == line.indexOf("href=", startFrom));
                    if(hrefIndex < 0 || hrefIndex + 5 >= line.length()){
                        break;
                    }

                    char quote = line.charAt(hrefIndex + 5);//skips lines that do not contain a quote after "href="
                    //because those are not links
                    if (quote != '"' && quote != '\'') {
                        startFrom = hrefIndex + 5;//avoids putting "href=" in the printed links
                        continue;
                    }

                    int start = hrefIndex + 6; //"href="" has 6 characters, so this line makes sure they are not printed
                    int end = line.indexOf(quote, start);//finds the closing quote
                    if (end < 0) {
                        break;
                    }
                    String link = line.substring(start, end);//cuts out text between the two quote marks. that text is the link

                    link = link.replace("&amp;", "&");//prints links with query strings correctly

                    if (link.startsWith("//")) {//prints links that start with "//"
                        link = "https:" + link;
                    } else if (link.startsWith("/")) {//prints links that start with "/"
                        link = "https://" + url.getHost() + link;
                    }

                    if (link.startsWith("http") && link.toLowerCase().contains(filter)) {//only prints real web links
                        System.out.println(link);//prints the link to the console
                        count++;
                    }

                    startFrom = end + 1;//lets several links on one line be found

                }
            }
            reader.close();//closes connection to website
        } catch (Exception ex) {
            System.out.println(ex);//prints links in output box
        }
    }
}