package springjpa.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springjpa.domain.Movie;
import springjpa.domain.validators.MovieProjectException;
import springjpa.domain.validators.MovieValidator;
import springjpa.domain.validators.ValidatorException;
import springjpa.repository.dbRepository.ClientRepository;
import springjpa.repository.dbRepository.MovieRepository;
import springjpa.repository.dbRepository.RentRepository;
import springjpa.utils.Sort;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ServiceMovie {

    public static final Logger log = LoggerFactory.getLogger(ServiceClient.class);
    @Autowired
    private MovieRepository repositoryMovie;
    @Autowired
    private ClientRepository repositoryClient;
    @Autowired
    private RentRepository repositoryRent;
    @Autowired
    private MovieValidator movieValidator;

    public List<Movie> getSortedMovies() {
        log.trace("Service - getSortedMovies --- method entered");
        //Sort sort = new Sort("title"); //sort asc by title
        //Sort sort = new Sort("genre"); //sort asc by genre

        //sort desc by genre and desc by title (should work with any number of fields)
        Sort sort = new Sort(Sort.direction.DESC, "genre", "title");
        //sort desc by genre and asc by title
        //Sort sort = new Sort(Sort.direction.DESC, "genre").and(new Sort("title"));
        List<Movie> movieList = StreamSupport.stream(repositoryMovie.findAll(sort).spliterator(), false)
                .collect(Collectors.toList());
        log.trace("Service - getSortedMovies --- movies = {}", movieList);
        return movieList;
    }

    /**
     * Adds a movie in the service.
     *
     * @param movie Movie
     * @throws MovieProjectException if movie is <code>null</code> | if movie is not valid. | if couldn't save to text file | if couldn't save to xml file
     */
    public void addMovie(Movie movie) throws MovieProjectException {
        log.trace("Service - addMovie --- method entered");
        movieValidator.validate(movie);
        Movie result = repositoryMovie.findById(movie.getId()).orElse(movie);
        Optional<Boolean> movieValidator = Optional.of(result.equals(movie));
        log.trace("Service - addMovie --- result = {}", result);
        movieValidator.filter(v -> v).orElseThrow(() -> new MovieProjectException("id is used"));
        repositoryMovie.save(movie);
    }

    /**
     * Removes a movie from the service given its id.
     *
     * @param id Integer
     * @throws MovieProjectException if id is <code>null</code>
     */
    public void deleteMovie(Integer id) throws MovieProjectException {
        log.trace("Service - deleteMovie --- method entered");
        try {
            this.repositoryMovie.findById(id).orElseThrow(() -> new ValidatorException("id not valid"));
            this.repositoryMovie.deleteById(id);
            this.repositoryRent.findAll().forEach(rent -> {
                if (rent.getMovieId() == id) this.repositoryRent.deleteById(rent.getId());
            });
            log.trace("Service - deleteMovie --- delete successful, id = {}", id);
        } catch (Exception e) {
            throw new MovieProjectException(e.getMessage(), e);
        }
    }

    /**
     * Updates a movie in the service.
     *
     * @param movie Movie
     * @throws MovieProjectException if movie is <code>null</code> | if movie is not valid,returns entity object.  (valid when null is returned)
     *                               | if couldn't update the text file | if couldn't update the xml file
     */
    public void updateMovie(Movie movie) throws MovieProjectException {
        log.trace("Service - updateMovie --- method entered");
        movieValidator.validate(movie);
        repositoryMovie.findById(movie.getId()).orElseThrow(() -> new MovieProjectException("id not existing"));
        Movie result = repositoryMovie.save(movie);
        log.trace("Service - updateMovie --- movie updated successfuly. \nresult = {}", result);
    }

    /**
     * Returns the set of movies.
     *
     * @return Set of movies
     */
    public Set<Movie> getAllMovies() {
        log.trace("Service - getAllMovies --- method entered");

        Set<Movie> movies = new HashSet<>(repositoryMovie.findAll());

        log.trace("Service - getAllMovies --- movies = {}", movies);
        return movies;

    }
}
