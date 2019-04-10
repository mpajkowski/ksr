package ksr.classifier;

import java.util.Map;

public class TaxiCabMetric implements Metric {
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var distance = 0.0;

        for (var it : vecA.entrySet()) {
            var index = it.getKey();
            var value = it.getValue();

            if (vecB.containsKey(index)) {
                distance += Math.abs(value - vecB.get(index));
            } else {
                distance += value;
            }
        }

        return distance;
    }
}
