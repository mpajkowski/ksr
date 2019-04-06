package ksr.classifier;

import ksr.data.ProcessedArticle;

import java.util.List;

public interface Classifier {
    void classify(List<ProcessedArticle> trainingSet, List<ProcessedArticle> testSet);
}
