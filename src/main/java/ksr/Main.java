package ksr;

import ksr.classifier.Classifier;
import ksr.classifier.KNNClassifier;
import ksr.classifier.Metric;
import ksr.classifier.TaxiCabMetric;
import ksr.data.*;

import java.io.IOException;
import java.lang.reflect.Member;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
      var parser = new ReutersDataParser();

      List<Article> articles = null;
      List<String> stoplist = null;
      try {
          articles = parser.parse("data/reut2-000.sgm");
          articles.addAll(parser.parse("data/reut2-001.sgm"));
          articles.addAll(parser.parse("data/reut2-002.sgm"));
          articles.addAll(parser.parse("data/reut2-003.sgm"));
          articles.addAll(parser.parse("data/reut2-004.sgm"));
          articles.addAll(parser.parse("data/reut2-005.sgm"));
          articles.addAll(parser.parse("data/reut2-006.sgm"));
          articles.addAll(parser.parse("data/reut2-007.sgm"));
          articles.addAll(parser.parse("data/reut2-008.sgm"));
          articles.addAll(parser.parse("data/reut2-009.sgm"));
          articles.addAll(parser.parse("data/reut2-010.sgm"));
          articles.addAll(parser.parse("data/reut2-011.sgm"));
          articles.addAll(parser.parse("data/reut2-012.sgm"));
          articles.addAll(parser.parse("data/reut2-013.sgm"));
          articles.addAll(parser.parse("data/reut2-014.sgm"));
          articles.addAll(parser.parse("data/reut2-015.sgm"));
          articles.addAll(parser.parse("data/reut2-016.sgm"));
          articles.addAll(parser.parse("data/reut2-017.sgm"));
          articles.addAll(parser.parse("data/reut2-018.sgm"));
          articles.addAll(parser.parse("data/reut2-019.sgm"));
          articles.addAll(parser.parse("data/reut2-020.sgm"));
          articles.addAll(parser.parse("data/reut2-021.sgm"));

          stoplist = Arrays.asList(Files.readString(Paths.get("data/stopwords-en.txt")).split("\n"));
      } catch (IOException e) {
          e.printStackTrace();
          System.exit(1);
      }

      var preprocessor = new Preprocessor(stoplist);
      articles = articles.stream()
              .filter(article -> article.getPlaces().size() == 1)
              .collect(Collectors.toList());



      articles.forEach(preprocessor::preprocess);
      var extractor = new TFIDFExtractor(articles);
      var processedArticles = extractor.extractFeatures();
      var articlesCount = processedArticles.size();

      List<ProcessedArticle> trainingSet = new ArrayList<>();
      List<ProcessedArticle> testSet = new ArrayList<>();

      int trainingCount = (int)(articlesCount * 0.6);

      for (int i = 0; i < trainingCount; ++i) {
          trainingSet.add(processedArticles.get(i));
      }

      for (int i = trainingCount; i < articlesCount; ++i) {
          testSet.add(processedArticles.get(i));
      }

      Metric metric = new TaxiCabMetric();
      Classifier classifier = new KNNClassifier(metric, 10);
      classifier.classify(trainingSet, testSet);

      double acc = 0;
      for (var testSample : testSet) {
          if (testSample.getClassifiedLabel().equals(testSample.getLabel())) {
              ++acc;
          }
      }

      acc /= testSet.size();

      System.out.println("ACCURACY: " + acc);
  }
}
