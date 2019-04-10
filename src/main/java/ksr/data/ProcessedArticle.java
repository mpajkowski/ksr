package ksr.data;

import java.util.Map;

public class ProcessedArticle {
    private Map<Integer, Double> vector;
    private String label;
    private String classifiedLabel;

    ProcessedArticle(Map<Integer,Double> vector, String label) {
        this.vector = vector;
        this.label = label;
        this.classifiedLabel = "";
    }

    public String getLabel() {
        return label;
    }

    public String getClassifiedLabel() {
        return classifiedLabel;
    }

    public void setClassifiedLabel(String classifiedLabel) {
        this.classifiedLabel = classifiedLabel;
    }

    public Map<Integer, Double> getVector() {
        return vector;
    }

    public void setVector(Map<Integer, Double> vector) {
        this.vector = vector;
    }
}
