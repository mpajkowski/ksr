package ksr.classifier;

import java.util.Map;

public class TchebychevMetric implements Metric{
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var max = 0.0;

        for (var entry: vecA.entrySet()) {
            var key = entry.getKey();
            var valA = entry.getValue();
            var valB = vecB.containsKey(key) ? vecB.get(key) : 0;

            max = Math.max(Math.abs(valA - valB), max);
        }

        for (var entry: vecB.entrySet()) {
            var key = entry.getKey();
            var val = entry.getValue();

            if (!vecA.containsKey(key)) {
                max = Math.max(Math.abs(val), max);
            }
        }

        return max;
    }
}

