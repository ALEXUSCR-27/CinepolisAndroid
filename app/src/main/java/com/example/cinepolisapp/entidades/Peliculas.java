package com.example.cinepolisapp.entidades;

import java.util.List;

public class Peliculas {
    private int FilmID;
    private String Title;

    public Peliculas(int filmID, String title) {
        FilmID = filmID;
        Title = title;
    }

    public int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int filmID) {
        FilmID = filmID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
