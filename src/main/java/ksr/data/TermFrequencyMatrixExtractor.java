package ksr.data;

import java.util.*;
import java.util.stream.Collectors;

public class TermFrequencyMatrixExtractor implements Extractor {
    private List<Article> corpus;

    public TermFrequencyMatrixExtractor(List<Article> corpus) {
        this.corpus = corpus;
    }

    @Override
    public List<ProcessedArticle> extractFeatures() {
        List<String> wordsFromCorpus = corpus.stream()
                .flatMap(articleList -> articleList.getText().stream())
                .collect(Collectors.toList());

        List<String> distinctWords = wordsFromCorpus.stream()
                .distinct()
                .collect(Collectors.toList());

        List<ProcessedArticle> processedArticles = new ArrayList<>();

        for (var article : corpus) {
            Map<Integer, Double> vec = new HashMap<>();
            var text = article.getText();

            for (var word : text) {
                vec.put(distinctWords.indexOf(word), (double) Collections.frequency(text, word));
            }

            processedArticles.add(new ProcessedArticle(vec, article.getPlace()));
        }

        return processedArticles;
    }
}
