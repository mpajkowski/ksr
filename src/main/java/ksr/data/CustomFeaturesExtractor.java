package ksr.data;

import java.util.*;
import java.util.stream.Collectors;

public class CustomFeaturesExtractor implements Extractor {
    private List<Article> corpus;

    public CustomFeaturesExtractor(List<Article> corpus) {
        this.corpus = corpus;
    }

    @Override
    public List<ProcessedArticle> extractFeatures() {
        List<ProcessedArticle> processedArticles = new ArrayList<>();

        List<String> distinctWords = corpus.stream()
                .flatMap(articleList -> articleList.getText().stream())
                .distinct()
                .collect(Collectors.toList());

        for (var article : corpus) {
            Map<Integer, Double> vec = new HashMap<>();
            vec.put(0, wordCount(article));
            processedArticles.add(new ProcessedArticle(vec, article.getPlace()));
        }
        return processedArticles;
    }

    private Double wordCount(Article article) {
        return (double) article.getText().size();
    }
}
