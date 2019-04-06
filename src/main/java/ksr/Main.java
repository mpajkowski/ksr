package ksr;

import ksr.data.Article;
import ksr.data.Preprocessor;
import ksr.data.ReutersDataParser;
import ksr.data.TFIDFExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
      var parser = new ReutersDataParser();

      List<Article> articles = null;
      List<String> stoplist = null;
      try {
          articles = parser.parse("data/reut2-000.sgm");
          stoplist = Arrays.asList(Files.readString(Paths.get("data/stopwords-en.txt")).split("\n"));
      } catch (IOException e) {
          e.printStackTrace();
      }

      var preprocessor = new Preprocessor(stoplist);
      articles.forEach(preprocessor::preprocess);
      var extractor = new TFIDFExtractor(articles);

      var processedArticles = extractor.extractFeatures();

      System.out.println("Processed article words: ");
      for (var item : processedArticles) {
          System.out.println(item.getVector());
      }
  }
}
