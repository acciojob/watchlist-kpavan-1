package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody(required = true) Movie movie){
        movieService.addMovie(movie);
      return new ResponseEntity("New Movie Added Successfully",HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody(required = true) Director director){
        movieService.addDirector(director);
        return new ResponseEntity("New Director Added Successfully",HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestBody() String movie,@RequestBody() String director){
        movieService.createMovieDirectorPair(director,movie);
        return new ResponseEntity("Director Movie pair created Successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
         Movie movie=movieService.findMovie(name);
        return new ResponseEntity(movie,HttpStatus.ACCEPTED);
    }


    @GetMapping("movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director = movieService.findDirector(name);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }


    @GetMapping(" /movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> movies = movieService.findMoviesFromDirector(director);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

   @GetMapping("/movies/get-all-movies")
   public ResponseEntity<List<String>> findAllMovies(){
       List<String> movies = movieService.findAllMovies();
       return new ResponseEntity<>(movies, HttpStatus.CREATED);
   }

   @DeleteMapping("/movies/delete-director-by-name")
   public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
       movieService.deleteDirector(director);
       return new ResponseEntity<>(director + " removed successfully", HttpStatus.CREATED);
   }

   @DeleteMapping("/movies/delete-all-directors")
   public ResponseEntity<String> deleteAllDirectors(){
       movieService.deleteAllDirectors();
       return new ResponseEntity<>("All directors deleted successfully", HttpStatus.CREATED);
   }



}
