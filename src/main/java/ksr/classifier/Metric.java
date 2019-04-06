package ksr.classifier;

import java.util.Map;

public interface Metric {
    double computeDistance(Map<Integer, Double> vecA, Map<Integer, Double> vecB);
}
