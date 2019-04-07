package ksr.classifier;

import java.util.Map;

public class TaxiCabMetric implements Metric {
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var distance = 0.0;

        // iterate over the first vector and try to match pairs
        for (var it : vecA.entrySet()) {
            var index = it.getKey();
            var value = it.getValue();

            if (vecB.containsKey(index)) {
                distance += Math.abs(value - vecB.get(index));
            } else {
                distance += Math.abs(value);
            }
        }

        return distance;
    }
}
