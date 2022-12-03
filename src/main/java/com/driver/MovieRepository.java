package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String,Movie> movieMap =new HashMap<>();
    private HashMap<String,Director> directorMap = new HashMap<>();
    private HashMap<String, List<String>> directorMovie = new HashMap<>();

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(),director);
    }

    public void saveMovieDirector(String movie, String director) {
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)) {
            List<String> currMovie = new ArrayList<>();
            if (directorMovie.containsKey(director)) currMovie = directorMovie.get(director);
            currMovie.add(movie);
            directorMovie.put(director, currMovie);
        }
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> movieList =new ArrayList<>();
        if(directorMap.containsKey(director)) movieList=directorMovie.get(director);
        return movieList;
    }

    public List<String> getAllMovies(){
        return  new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String director){
        List<String> movies=new ArrayList<>();
        if(directorMovie.containsKey(director)){
            movies=directorMovie.get(director);
        }
        for(String movie:movies){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        if(directorMovie.containsKey(director))
        directorMovie.remove(director);

    }

    public void deleteAllDirectors(){
        for(String director : directorMap.keySet()){
            deleteDirectorByName(director);
        }
    }
}
