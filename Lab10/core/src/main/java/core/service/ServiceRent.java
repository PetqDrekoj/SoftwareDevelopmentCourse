package core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import core.domain.Client;
import core.domain.Movie;
import core.domain.Rent;
import core.domain.validators.MovieProjectException;
import core.domain.validators.Validator;
import core.repository.dbRepository.ClientRepository;
import core.repository.dbRepository.MovieRepository;
import core.repository.dbRepository.RentRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ServiceRent {

    public static final Logger log = LoggerFactory.getLogger(ServiceClient.class);
    @Autowired
    private MovieRepository repositoryMovie;
    @Autowired
    private ClientRepository repositoryClient;
    @Autowired
    private RentRepository repositoryRent;
    @Autowired
    private Validator<Rent> validator;

    public void addRent(Rent rent) throws MovieProjectException {
        log.trace("Service - addRent --- method entered");
        validator.validate(rent);
        Iterable<Movie> movies = repositoryMovie.findAll();
        Iterable<Client> clients = repositoryClient.findAll();
        List<Movie> filteredMovies = StreamSupport.stream(movies.spliterator(), false)
                .filter(movie -> movie.getId().equals(rent.getMovieId())).collect(Collectors.toList());
        List<Client> filteredClients = StreamSupport.stream(clients.spliterator(), false)
                .filter(client -> client.getId().equals(rent.getClientId())).collect(Collectors.toList());
        filteredMovies.stream().findAny().orElseThrow(() -> new MovieProjectException("There's no movie with that id!"));
        filteredClients.stream().findAny().orElseThrow(() -> new MovieProjectException("There's no client with that id!"));

        Rent result = repositoryRent.findById(rent.getId()).orElse(rent);
        Optional<Boolean> rentValidator = Optional.of(result.equals(rent));
        rentValidator.filter(v -> v).orElseThrow(() -> new MovieProjectException("id is used"));
        repositoryRent.save(rent);
        log.trace("Service - addRent --- successful");
    }

    public Set<Rent> getAllRents() {
        log.trace("Service - getAllRents --- method entered");

        Set<Rent> rents = new HashSet<>(repositoryRent.findAll());

        log.trace("Service - getAllRents --- movies = {}", rents);
        return rents;
    }

    public Set<String> filterMoviesIfRented() {
        log.trace("Service - filterMoviesIfRented --- method entered");
        Iterable<Movie> movies = repositoryMovie.findAll();
        Iterable<Rent> rents = repositoryRent.findAll();
        Set<Integer> rentedMoviesIds = StreamSupport.stream(rents.spliterator(), false).map(Rent::getMovieId).collect(Collectors.toSet());
        Set<String> result = StreamSupport.stream(movies.spliterator(), false).filter(movie -> rentedMoviesIds.contains(movie.getId())).map(Movie::getTitle).collect(Collectors.toSet());
        log.trace("Service - findMostRentedMovie --- result = {}", result);
        return result;
    }

    public String findMostRentedMovie() {
        log.trace("Service - findMostRentedMovie --- method entered");
        Iterable<Rent> rents = repositoryRent.findAll();
        List<Integer> rentedMoviesIds = StreamSupport.stream(rents.spliterator(), false).map(Rent::getMovieId).collect(Collectors.toList());
        String result = repositoryMovie.findById(rentedMoviesIds.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get().getKey()).get().getTitle();
        log.trace("Service - findMostRentedMovie --- result = {}", result);
        return result;
    }
}
