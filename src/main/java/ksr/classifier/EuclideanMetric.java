package ksr.classifier;

import java.util.Map;

public class EuclideanMetric implements Metric{
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var distance = 0.0;

        for (var entry: vecA.entrySet()) {
            var index = entry.getKey();
            var value = entry.getValue();

            if (vecB.containsKey(index)) {
                distance += Math.pow(vecB.get(index) - value, 2);
            } else {
                distance += Math.pow(value, 2);
            }
        }

        return Math.sqrt(distance);
    }
}
