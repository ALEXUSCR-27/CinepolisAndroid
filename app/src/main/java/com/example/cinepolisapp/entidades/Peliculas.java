package com.example.cinepolisapp.entidades;

import java.util.List;

public class Peliculas {
    private String movieID;
    private String title;
    private String director;
    private String actors;
    private String languages;
    private String genres;
    private String runtimeMin;
    private String year;
    private String ageRequired;
    private String priceN;
    private String priceG;
    private String priceM;
    private boolean display;

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRuntimeMin() {
        return runtimeMin;
    }

    public void setRuntimeMin(String runtimeMin) {
        this.runtimeMin = runtimeMin;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAgeRequired() {
        return ageRequired;
    }

    public void setAgeRequired(String ageRequired) {
        this.ageRequired = ageRequired;
    }

    public String getPriceN() {
        return priceN;
    }

    public void setPriceN(String priceN) {
        this.priceN = priceN;
    }

    public String getPriceG() {
        return priceG;
    }

    public void setPriceG(String priceG) {
        this.priceG = priceG;
    }

    public String getPriceM() {
        return priceM;
    }

    public void setPriceM(String priceM) {
        this.priceM = priceM;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}