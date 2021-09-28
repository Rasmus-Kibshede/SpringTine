package com.example.springtinemvc.controller;

import com.example.springtinemvc.repository.DBManager;
import com.example.springtinemvc.repository.Movie;
import com.example.springtinemvc.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;

@RestController
//@Controller
public class MovieController {

    private MovieRepository movieRepository = new MovieRepository();

    public Movie getMovie() {
        return movie;
    }

    private Movie movie = new Movie();

    @GetMapping()
    public String welcomeText() {
        return "Welcome to the website";
    }


    @GetMapping("/check")
    //@ResponseBody
    public String checkConnection() {
        Connection connection = DBManager.getConnection();
        if (connection != null) {
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/first")
    public String getFirstMovie() {
        return movieRepository.getFirstMovie();
    }

    @GetMapping("/random")
    public String randomMovie() {
        return movieRepository.getRandomMovie();
    }

    @GetMapping("/getTenSortByPopularity")
    public String getTenSortByPopularity() {
        return movieRepository.getTenSortByPopularity();
    }

    @GetMapping("/howManyWonAnAward")
    public int howManyWonAnAward() {
        return movieRepository.howManyWonAnAward();
    }

    @GetMapping("/Advanced")
    public void Advanced(@RequestParam String title, @RequestParam int year, @RequestParam int length, @RequestParam String sub, @RequestParam int pop, @RequestParam String awards) {
        movie = new Movie(title, year, length, sub, pop, awards);
        movieRepository.advanced(movie);
    }


}
