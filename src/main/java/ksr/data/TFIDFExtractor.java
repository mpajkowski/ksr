package ksr.data;

import java.util.*;
import java.util.stream.Collectors;

public class TFIDFExtractor implements Extractor {
    private List<Article> corpus;

    public TFIDFExtractor(List<Article> corpus) {
        this.corpus = corpus;
    }

    @Override
    public List<ProcessedArticle> extractFeatures() {
        var processedArticleList = new ArrayList<ProcessedArticle>();

        List<String> wordsFromCorpus = corpus.stream()
                .flatMap(articleList -> articleList.getText().stream())
                .collect(Collectors.toList());

        List<String> distinctWords = wordsFromCorpus.stream()
                .distinct()
                .collect(Collectors.toList());

        Map<String, Long> corpusWordsCount = createCountMap(wordsFromCorpus);

        for (var article : corpus) {
            SortedMap<Integer, Double> vector = new TreeMap<>();
            var articleWordsCount = createCountMap(article.getText());

            for (var word : article.getText()) {
                var termFrequency = articleWordsCount.get(word) / (double) article.getText().size();
                var inverseDocumentFrequency = Math.log(corpus.size() / (double) corpusWordsCount.get(word));

                var tfxidf = termFrequency * inverseDocumentFrequency;
                vector.put(distinctWords.indexOf(word), tfxidf);
            }

            processedArticleList.add(new ProcessedArticle(vector,
                    article.getPlace())); // take the first label
        }

        return processedArticleList;
    }

    private Map<String, Long> createCountMap(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(word -> word,
                        Collectors.counting()));
    }
}
