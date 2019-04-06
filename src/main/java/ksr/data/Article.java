package ksr.data;

import java.util.List;

public class Article {
    private List<String> places;
    private String title;
    private String body;
    private List<String> text;

    public List<String> getPlaces() {
        return places;
    }

    public List<String> getText() {
        return text;
    }

    public Article(List<String> places, String title, String body) {
        this.places = places;
        this.title = title;
        this.body = body;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
