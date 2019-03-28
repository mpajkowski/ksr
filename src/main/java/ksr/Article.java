package ksr;

import java.util.List;

public class Article {
    private List<String> places;
    private String title;
    private String text;

    public List<String> getPlaces() {
        return places;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public Article(List<String> places, String title, String text) {
        this.places = places;
        this.title = title;
        this.text = text;
    }
}
