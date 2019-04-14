package ksr.classifier;

import java.util.Map;

public class TchebychevMetric implements Metric{
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var max = 0.0;

        for (var entry: vecA.entrySet()) {
            var key = entry.getKey();
            var val = entry.getValue();

            var current = 0.0;

            if (vecB.containsKey(key)) {
                current = Math.abs(vecB.get(key) - val);
            } else {
                current = val;
            }

            max = Math.max(max, current);
        }

        return max;
    }
}

