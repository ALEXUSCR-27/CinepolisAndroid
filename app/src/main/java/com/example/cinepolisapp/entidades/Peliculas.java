package com.example.cinepolisapp.entidades;

import java.io.Serializable;
import java.util.List;

public class Peliculas implements Serializable {
    private int movieID;
    private String title;
    private String director;
    private String actors;
    private String languages;
    private String genres;
    private int runtimeMin;
    private int year;
    private int ageRequired;
    private int priceN;
    private int priceG;
    private int priceM;
    private boolean display;

    public Peliculas(int movieID, String title, String director, String actors, String languages, String genres, int runtimeMin, int year, int ageRequired, int priceN, int priceG, int priceM, boolean display) {
        this.movieID = movieID;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.languages = languages;
        this.genres = genres;
        this.runtimeMin = runtimeMin;
        this.year = year;
        this.ageRequired = ageRequired;
        this.priceN = priceN;
        this.priceG = priceG;
        this.priceM = priceM;
        this.display = display;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
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

    public int getRuntimeMin() {
        return runtimeMin;
    }

    public void setRuntimeMin(int runtimeMin) {
        this.runtimeMin = runtimeMin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAgeRequired() {
        return ageRequired;
    }

    public void setAgeRequired(int ageRequired) {
        this.ageRequired = ageRequired;
    }

    public int getPriceN() {
        return priceN;
    }

    public void setPriceN(int priceN) {
        this.priceN = priceN;
    }

    public int getPriceG() {
        return priceG;
    }

    public void setPriceG(int priceG) {
        this.priceG = priceG;
    }

    public int getPriceM() {
        return priceM;
    }

    public void setPriceM(int priceM) {
        this.priceM = priceM;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}