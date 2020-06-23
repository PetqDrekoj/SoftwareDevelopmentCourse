package web.controller;

import core.service.ServiceMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.MovieConverter;
import web.dto.ClientDto;
import web.dto.MovieDto;
import web.dto.MoviesDto;

@RestController
public class MovieController {

    public static final Logger log= LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private ServiceMovie serviceMovie;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDto getAllMovies() {
        log.trace("Controller - getAllMovies - entered");
        MoviesDto movies = new MoviesDto(movieConverter.convertModelsToDtos(serviceMovie.getAllMovies()));
        log.trace("Controller - getAllMovies - movies: \n {}", movies);
        return movies;
    }

    @RequestMapping(value = "/movies_sorted", method = RequestMethod.GET)
    MoviesDto getSortedMovies() {
        log.trace("Controller - getSortedMovies - entered");
        MoviesDto movies =  new MoviesDto(movieConverter.convertModelsToDtos(serviceMovie.getSortedMovies()));
        log.trace("Controller - getSortedMovies - movies: \n {}", movies);
        return movies;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    void addMovie(@RequestBody MovieDto movieDto) {
        log.trace("Controller - addMovie - movie: {}",movieDto);
        try{
            serviceMovie.addMovie(movieConverter.convertDtoToModel(movieDto));
            log.trace("Controller - movie added");
        }
        catch (Exception e) {
            log.trace("Controller - movie add failed. Error: \n {}",e.toString());
        }

    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    void updateMovie(@PathVariable Integer id,
                       @RequestBody MovieDto movieDto) {
        log.trace("Controller - updateMovie - id: {} movie: {}",id,movieDto);
        try {
            serviceMovie.updateMovie(movieConverter.convertDtoToModel(movieDto));
            log.trace("Controller - movie updated");
        }
        catch (Exception e) {
            log.trace("Controller - movie update failed. Error: \n {}",e.toString());
        }
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        log.trace("Controller - updateMovie - id: {}",id);
        try{
            serviceMovie.deleteMovie(id);
            log.trace("Controller - movie deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e) {
            log.trace("Controller - movie delete failed. Error: \n {}",e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
