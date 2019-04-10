package ksr.data;

import ksr.data.Article;
import ksr.data.DataParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReutersDataParser implements DataParser<Article> {
    private static final Pattern REUTERS_PATTERN = Pattern.compile(
            "<PLACES>(.*?)</PLACES>.*?" +
                    "<TITLE>(.*?)</TITLE>.*?" +
                    "<BODY>(.*?)</BODY>",
            Pattern.DOTALL
    );

    private static final Pattern LIST_PATTERN = Pattern.compile(
            "<D>(.*?)</D>"
    );

    public List<Article> parse(String path) throws IOException {
        var articles = new ArrayList<Article>();

        String fileContent = new String(Files.readAllBytes(Paths.get(path)));

        var articleMatcher = REUTERS_PATTERN.matcher(fileContent);

        while (articleMatcher.find()) {
            var title = articleMatcher.group(2)
                    .replaceAll("&lt;", "<");
            var text = articleMatcher.group(3)
                    .replaceAll("&lt;", "<");

            var places = new ArrayList<String>();
            var placesMatcher = LIST_PATTERN.matcher(articleMatcher.group(1));

            while (placesMatcher.find()) {
                places.add(placesMatcher.group(1));
            }

            if (places.size() > 0) {
                articles.add(new Article(places.get(0), title, text));
            }
        }

        return articles;
    }
}
