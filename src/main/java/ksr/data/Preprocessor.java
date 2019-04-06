package ksr.data;

import ca.rmen.porterstemmer.PorterStemmer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Preprocessor {
    private List<String> stoplist;

    public Preprocessor(List<String> stoplist) {
        this.stoplist = stoplist;
    }

    public void preprocess(Article article) {
        var text = preprocessSection(article.getTitle());
        text.addAll(preprocessSection(article.getBody()));

        article.setText(text);
    }

    private List<String> preprocessSection(String text) {
        ArrayList<String> words = new ArrayList<String>();
        var processed = text.replaceAll("[^A-Za-z]+", " ");
        var stemmer = new PorterStemmer();
        for (var word : processed.split(" ")) {
            if (stoplist.stream().anyMatch(match -> match.equals(word))) {
                continue;
            }

            words.add(stemmer.stemWord(word));
        }
        return words;
    }
}
