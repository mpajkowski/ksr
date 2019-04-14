package ksr.classifier;

import java.util.Map;

public class TaxiCabMetric implements Metric {
    @Override
    public double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB) {
        var distance = 0.0;

        for (var it : vecA.entrySet()) {
            var index = it.getKey();
            var valueA = it.getValue();
            var valueB = vecB.containsKey(index) ? vecB.get(index) : 0;

            distance += Math.abs(valueA - valueB);
        }

        for (var it: vecB.entrySet()) {
            if (!vecA.containsKey(it.getKey())) {
                distance += Math.abs(it.getValue());
            }
        }

        return distance;
    }
}
