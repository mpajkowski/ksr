package ksr.data;

import java.util.*;
import java.util.stream.Collectors;

public class DistanceFromBeginExtractor implements Extractor {
    private List<Article> corpus;

    public DistanceFromBeginExtractor(List<Article> corpus) {
        this.corpus = corpus;
    }

    @Override
    public List<ProcessedArticle> extractFeatures() {
        List<String> distinctWords = corpus.stream()
                .flatMap(articleList -> articleList.getText().stream())
                .distinct()
                .collect(Collectors.toList());

        List<ProcessedArticle> processedArticles = new ArrayList<>();

        for (var article : corpus) {
            Map<Integer, Double> vec = new HashMap<>();

            var distinctWordsFromArticle = article.getText().stream().distinct().collect(Collectors.toList());
            for (var word : distinctWordsFromArticle) {
                vec.put(distinctWords.indexOf(word), (double) distinctWordsFromArticle.indexOf(word));
            }
            processedArticles.add(new ProcessedArticle(vec, article.getPlace()));
        }

        return processedArticles;
    }
}
