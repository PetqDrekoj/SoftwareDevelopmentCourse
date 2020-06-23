package core.service;


import core.domain.BaseEntity;
import core.domain.Client;
import core.domain.Movie;
import core.domain.Rent;
import core.domain.validators.MovieProjectException;
import core.repository.dbRepository.ClientRepository;
import core.repository.dbRepository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
public class ServiceMovie {

    public static final Logger log = LoggerFactory.getLogger(ServiceMovie.class);
    @Autowired
    private MovieRepository repositoryMovie;
    @Autowired
    private ClientRepository repositoryClient;



    public void addMovie(Movie movie) throws MovieProjectException {
        log.trace("Service - addMovie --- method entered");
            try {
                int id = repositoryMovie.findAllSimple().stream().map(BaseEntity::getId).mapToInt(v -> v).max().orElse(0);
                movie.setId(id + 1);
                repositoryMovie.save(movie);
            } catch (Exception e) {
                throw new MovieProjectException(e.toString());
            }
        }

    @Transactional
    public void deleteMovie(String movie_title) throws MovieProjectException {
        log.trace("deleteMovie - method entered: id={}", movie_title);
        Movie movie = this.getMovieByTitle(movie_title);
        if (movie != null) {
            for (Rent rent : movie.getRentals()) {
                Client client = rent.getClient();
                rent.setClient(null);
                rent.setMovie(null);
                Client c = this.getClientByName(client.getName());
                if (c != null) {
                    List<Rent> rents = c.getRentals();
                    rents.remove(rent);
                    c.setRentals(rents);
                }
            }
            repositoryMovie.deleteById(movie.getId());
            log.trace("deleteMovie - method finished");
        }
    }

    @Transactional
    public void updateMovie(String old_title, Movie movie) throws MovieProjectException {
        log.trace("Service - updateMovie --- method entered");
            Movie result = this.getMovieByTitle(old_title);
            if (result == null) throw new MovieProjectException("no name with this title");
            movie.setId(result.getId());
            repositoryMovie.updateMovie(movie);
            log.trace("Service - updateMovie --- movie updated successfuly. \nresult = {}", result);
    }


    public List<Movie> getAllMovies() {
        return this.repositoryMovie.findAllWithRentsAndClients();
    }

    public Client getClientByName(String name) {
        return repositoryClient.findAll().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public Movie getMovieByTitle(String title) {
        return repositoryMovie.findAll().stream().filter(c -> c.getTitle().equals(title)).findFirst().orElse(null);
    }
}
