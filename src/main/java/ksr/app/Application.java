package ksr.app;

import ksr.classifier.Classifier;
import ksr.classifier.KNNClassifier;
import ksr.classifier.Metric;
import ksr.classifier.TaxiCabMetric;
import ksr.data.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static List<String> paths;
    private static List<String> places;
    private static int K_PARAM;


    public static void run() {
        var parser = new ReutersDataParser();

        List<Article> articles = new ArrayList<>();
        List<String> stoplist = null;
        try {
            for (var path : paths) {
                articles.addAll(parser.parse(path));
            }

            System.out.println("Parsing done...");
            stoplist = Arrays.asList(Files.readString(Paths.get("data/stopwords-en.txt")).split("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        var preprocessor = new Preprocessor(stoplist);

        articles = articles.stream()
                .filter(article -> {
                    for (var place : places) {
                        if (place.equals(article.getPlace())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());

        articles.forEach(preprocessor::preprocess);
        //var extractor = new TFIDFExtractor(articles);
        var extractor = new DistanceFromBeginExtractor(articles);
        var processedArticles = extractor.extractFeatures();
        var articlesCount = processedArticles.size();

        List<ProcessedArticle> trainingSet = new ArrayList<>();
        List<ProcessedArticle> testSet = new ArrayList<>();

        int trainingCount = (int) (articlesCount * 0.6);

        for (int i = 0; i < trainingCount; ++i) {
            trainingSet.add(processedArticles.get(i));
        }

        for (int i = trainingCount; i < articlesCount; ++i) {
            testSet.add(processedArticles.get(i));
        }

        Metric metric = new TaxiCabMetric();
        Classifier classifier = new KNNClassifier(metric, K_PARAM);
        classifier.classify(trainingSet, testSet);

        int classifiedCounter = 0;
        for (var testSample : testSet) {
            if (testSample.getClassifiedLabel().equals(testSample.getLabel())) {
                ++classifiedCounter;
                System.out.println("OK: " + testSample.getLabel() + " eq " + testSample.getClassifiedLabel());
            } else {
                System.out.println("NOK:" + testSample.getLabel() + " != " + testSample.getClassifiedLabel());
            }
        }

        double accuracy = classifiedCounter / (double) testSet.size();

        System.out.println("ACCURACY: " + accuracy);
        System.out.println("Classified " + classifiedCounter + " out of " + testSet.size());
    }

    static {
        K_PARAM = 10;

        paths = new ArrayList<>();
        paths.add("data/reut2-000.sgm");
        paths.add("data/reut2-001.sgm");
        paths.add("data/reut2-002.sgm");
        paths.add("data/reut2-003.sgm");
        paths.add("data/reut2-004.sgm");
        paths.add("data/reut2-005.sgm");
        paths.add("data/reut2-006.sgm");
        paths.add("data/reut2-007.sgm");
        paths.add("data/reut2-008.sgm");
        paths.add("data/reut2-009.sgm");
        paths.add("data/reut2-010.sgm");
        paths.add("data/reut2-011.sgm");
        paths.add("data/reut2-012.sgm");
        paths.add("data/reut2-013.sgm");
        paths.add("data/reut2-014.sgm");
        paths.add("data/reut2-015.sgm");
        paths.add("data/reut2-016.sgm");
        paths.add("data/reut2-017.sgm");
        paths.add("data/reut2-018.sgm");
        paths.add("data/reut2-019.sgm");
        paths.add("data/reut2-020.sgm");
        paths.add("data/reut2-021.sgm");

        places = new ArrayList<>();
        places.add("west-germany");
        places.add("usa");
        places.add("france");
        places.add("uk");
        places.add("canada");
        places.add("japan");
    }
}
