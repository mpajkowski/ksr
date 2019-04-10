package ksr.data;

import java.util.List;

public class Article {
    private String place;
    private String title;
    private String body;
    private List<String> text;

    public String getPlace() {
        return place;
    }

    public List<String> getText() {
        return text;
    }

    public Article(String place, String title, String body) {
        this.place = place;
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
