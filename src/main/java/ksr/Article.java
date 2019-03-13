package ksr;

import java.util.List;

public class Article {
    private String title;
    private List<String> places;
    private String text;

    public List<String> getPlaces() {
        return places;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Article(String title, List<String> places, String text) {
        this.title = title;
        this.places = places;
        this.text = text;
    }
}
