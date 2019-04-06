package ksr.classifier;

import ksr.data.ProcessedArticle;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class KNNClassifier implements Classifier {
    private Metric metric;
    private final int k;

    KNNClassifier(Metric metric, int k) {
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

            List<String> nearest = distances.entrySet().stream()
                    .map(entry -> entry.getValue().getLabel())
                    .collect(Collectors.toList());
        }
    }
}
