package web.controller;

import core.domain.Movie;
import core.service.ServiceMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import web.converter.MovieConverter;
import web.dto.MovieDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    public static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private ServiceMovie serviceMovie;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    List<MovieDto> getAllMovies() {
        log.trace("Controller - getAllMovies - entered ");
        List<Movie> moviePages = serviceMovie.getAllMovies();
        List<MovieDto> movies = new ArrayList(movieConverter.convertModelsToDtos(moviePages));
        log.trace("Controller - getAllMovies - movies: \n {}", movies);
        return movies;
    }


    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public MovieDto addMovie(@RequestBody @Valid MovieDto movieDto) {
        log.trace("Controller - addMovie - movie: {}", movieDto);
        try {
            serviceMovie.addMovie(new Movie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getYear(),null));
            log.trace("Controller - movie added");
        } catch (Exception e) {
            log.trace("Controller - movie add failed. Error: \n {}", e.toString());
        }
        return movieDto;
    }

    @RequestMapping(value = "/movies/{movie_title}", method = RequestMethod.PUT)
    void updateMovie(@PathVariable @Min(0) @NotNull String movie_title,
                     @RequestBody @Valid MovieDto movieDto) {
        log.trace("Controller - updateMovie - id: {} movie: {}", movie_title, movieDto);
        try {
            serviceMovie.updateMovie(movie_title, new Movie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getYear(),null));
            log.trace("Controller - movie updated");
        } catch (Exception e) {
            log.trace("Controller - movie update failed. Error: \n {}", e.toString());
        }
    }

    @RequestMapping(value = "/movies/{movie_title}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable String movie_title) {
        log.trace("Controller - updateMovie - id: {}", movie_title);
        try {
            serviceMovie.deleteMovie(movie_title);
            log.trace("Controller - movie deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.trace("Controller - movie delete failed. Error: \n {}", e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
