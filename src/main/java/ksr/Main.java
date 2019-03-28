package ksr;

import java.io.IOException;
import java.util.List;

public class Main {
  public static void main(String[] args) {
      var extractor = new ReutersDataParser();

      List<Article> articles = null;
      try {
          articles = extractor.parse("data/reut2-000.sgm");
      } catch (IOException e) {
          e.printStackTrace();
      }

      int i = 0;
      for (var article : articles) {
          System.out.print(i++ + ": title: " + article.getTitle() + ", place(s): ");
          for (var place : article.getPlaces()) {
              System.out.print(place + " ");
          }
          System.out.println();
      }

      System.out.println(articles.size());
  }
}
