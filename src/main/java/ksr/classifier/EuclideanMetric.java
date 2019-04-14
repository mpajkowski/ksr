package ksr.classifier;

import java.util.Map;

public class EuclideanMetric implements Metric{
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var distance = 0.0;

        for (var entry: vecA.entrySet()) {
            var index = entry.getKey();
            var valueA = entry.getValue();
            var valueB = vecB.containsKey(index) ? vecB.get(index) : 0;

            distance += Math.pow(valueA - valueB, 2);
        }

        for (var entry: vecB.entrySet()) {
            var index = entry.getKey();
            var value = entry.getValue();

            if (!vecA.containsKey(index)) {
                distance += Math.pow(value, 2);
            }
        }

        return Math.sqrt(distance);
    }
}
