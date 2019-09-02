package com.laxtech.neo4jexample.controller;

import java.util.Collection;
import java.util.Map;

import com.laxtech.neo4jexample.domain.Movie;
import com.laxtech.neo4jexample.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return movieService.graph(limit == null ? 100 : limit);
    }

    @GetMapping("/movies/search/findByTitle")
    public Movie movie(@RequestParam(value = "title",required = true) String title) {
        return movieService.findByTitle(title);
    }

    @GetMapping("/movies/search/findByTitleLike")
    public Collection<Movie> movies(@RequestParam(value = "title",required = true) String title) {
        return movieService.findByTitleLike(title);
    }
}
