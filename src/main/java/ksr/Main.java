package ksr;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
      var extractor = new ReutersExtractor();

      ArrayList<Article> articles = null;
      try {
          articles = extractor.extractSgmFile("data/reut2-000.sgm");
      } catch (IOException e) {
          e.printStackTrace();
      }

      for (var article : articles) {
          System.out.println(article.getTitle());
          System.out.println("--------------------");
          for (var place : article.getPlaces()) {
              System.out.println(place);
          }
          System.out.println();
      }
  }
}
