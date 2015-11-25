package com.example.mostafa.whatshouldiwatch;


import java.util.List;

public class Movies {

    private int _id;
    private String _movieTitle;

    private List<String> _genre;

    public List<String> get_genre() {
        return _genre;
    }

    public void set_genre(List<String> _genre) {
        this._genre = _genre;
    }

    public Movies(String movieTitle) {
        this._movieTitle = movieTitle;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_movieTitle() {
        return _movieTitle;
    }

    public void set_movieTitle(String _movieTitle) {
        this._movieTitle = _movieTitle;
    }

}
