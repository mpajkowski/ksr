package ksr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReutersExtractor {
    static final Pattern REUTERS_PATTERN = Pattern.compile(
            String.format("<PLACES>(.*?)</PLACES>.*?<TITLE>(.*?)</TITLE>.*?<BODY>(.*?)</BODY>")
    );

    static final Pattern LIST_PATTERN = Pattern.compile(
            "<D>(.*?)</D>"
    );

    static final String ARTICLE_CLOSE_TAG = "</REUTERS>";

    public ArrayList<Article> extractSgmFile(String path) throws IOException {
        var articles = new ArrayList<Article>();

        var br = new BufferedReader(new FileReader(path));
        var buffer = new StringBuilder();

        for (var line = br.readLine();
             line != null;
             line = br.readLine()) {
            if (line.contains(ARTICLE_CLOSE_TAG)) {
                var matcher = REUTERS_PATTERN.matcher(buffer.toString());

                if (matcher.find()) {
                    var title = matcher.group(2).replaceAll("&lt;", "<");
                    var text = matcher.group(3).replaceAll("&lt;", "<");

                    var places = new ArrayList<String>();
                    var placesMatcher = LIST_PATTERN.matcher(matcher.group(1));

                    while (placesMatcher.find()) {
                        places.add(placesMatcher.group(1));
                    }

                    articles.add(new Article(title, places, text));
                }

                // clean up the buffer
                buffer = new StringBuilder();
                continue;
            }

            buffer.append(line);
        }

        return articles;
    }
}
