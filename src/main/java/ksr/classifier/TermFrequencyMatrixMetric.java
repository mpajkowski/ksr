package ksr.classifier;

import java.util.Map;

public class TermFrequencyMatrixMetric implements Metric {
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var numerator = 0.0;

        for (var entry: vecA.entrySet()) {
            var key = entry.getKey();
            var valueA = entry.getValue();
            var valueB = vecB.containsKey(key) ? vecB.get(key) : 0;
            numerator += valueA * valueB;
        }

        var sumOfSquaresA = 0;
        for (var entry: vecA.entrySet()) {
            sumOfSquaresA += Math.pow(entry.getValue(), 2);
        }

        var sumOfSquaresB = 0;
        for (var entry: vecB.entrySet()) {
            sumOfSquaresB += Math.pow(entry.getValue(), 2);
        }

        var denominator = Math.sqrt(sumOfSquaresA * sumOfSquaresB);
        var similarity = numerator / denominator;

        return 1 - similarity;
    }
}
