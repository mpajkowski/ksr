package ksr.classifier;

import ksr.data.ProcessedArticle;

import java.util.*;
import java.util.stream.Collectors;

public class KNNClassifier implements Classifier {
    private Metric metric;
    private final int k;

    public KNNClassifier(Metric metric, int k) {
        this.metric = metric;
        this.k = k;
    }

    public void classify(List<ProcessedArticle> trainingSet, List<ProcessedArticle> testSet) {
        for (var testSample : testSet) {
            SortedMap<Double, ProcessedArticle> distances = new TreeMap<>();

            for (var trainingSample : trainingSet) {
                distances.put(metric.computeDistance(testSample.getVector(),
                        trainingSample.getVector()), trainingSample);
            }

            List<String> nearestLabels = distances.entrySet().stream()
                    .limit(k)
                    .map(entry -> entry.getValue().getLabel())
                    .collect(Collectors.toList());

            Map<String, Long> nearestLabelsCount = nearestLabels.stream()
                    .collect(Collectors.groupingBy(label -> label,
                            Collectors.counting()));

            String predictedLabel = Collections.max(nearestLabelsCount.entrySet(),
                    Map.Entry.comparingByValue()).getKey();

            testSample.setClassifiedLabel(predictedLabel);
        }
    }
}
