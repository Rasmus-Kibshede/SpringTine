package com.example.springtinemvc.repository;

public class Movie {
    private int id;
    private String title;
    private int year;
    private int length;
    private String subject;
    private int popularity;
    private String awards;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public String getSubject() {
        return subject;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getAwards() {
        return awards;
    }

    public Movie(int id, String title, int year, int length, String subject, int popularity, String awards) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.length = length;
        this.subject = subject;
        this.popularity = popularity;
        this.awards = awards;
    }

    public Movie() {
    }

    public Movie(String title, int year, int length, String subject, int popularity, String awards) {
        this.title = title;
        this.year = year;
        this.length = length;
        this.subject = subject;
        this.popularity = popularity;
        this.awards = awards;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", length=" + length +
                ", subject='" + subject + '\'' +
                ", popularity=" + popularity +
                ", awards='" + awards + '\'' +
                '}';
    }
}
