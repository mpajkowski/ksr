package ksr;

import java.util.ArrayList;

public class Article {
    private String title;
    private ArrayList<String> places;
    private String text;

    public ArrayList<String> getPlaces() {
        return places;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Article(String title, ArrayList<String> places, String text) {
        this.title = title;
        this.places = places;
        this.text = text;
    }
}
