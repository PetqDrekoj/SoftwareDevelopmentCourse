package core.service;

import core.domain.Client;
import core.domain.Movie;
import core.domain.Rent;
import core.domain.validators.MovieProjectException;
import core.repository.dbRepository.ClientRepository;
import core.repository.dbRepository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceRent {

    public static final Logger log = LoggerFactory.getLogger(ServiceClient.class);
    @Autowired
    private MovieRepository repositoryMovie;
    @Autowired
    private ClientRepository repositoryClient;

    @Transactional
    public void addRent(Rent rent) throws MovieProjectException {
        log.trace("Service - addRent --- method entered");

        Movie m = repositoryMovie.findByTitle(rent.getMovie().getTitle());
        if (m != null) {
            List<Rent> rents = m.getRentals();
            rents.add(rent);
            m.setRentals(rents);
        }
        Client c = repositoryClient.findByName(rent.getClient().getName());
        if (c != null) {
            List<Rent> rents = c.getRentals();
            rents.add(rent);
            c.setRentals(rents);
        }
        log.trace("Service - addRent --- successful");
    }

    public Page<Rent> getAllRents(int page, int size, String containingString) {
        log.trace("Service - getAllRents --- method entered");

        List<Rent> rents = new ArrayList<Rent>();
        List<Movie> movies = this.repositoryMovie.findAllWithRentals();
        movies.forEach(movie -> rents.addAll(movie.getRentals()));
        Pageable pageable = PageRequest.of(page, size);
        Page<Rent> pages = new PageImpl<Rent>(rents, pageable, rents.size());
        log.trace("Service - getAllRents --- rents = {}", rents);
        return pages;
    }

    public Client getClientByName(String name) {
        return repositoryClient.findByName(name);
    }

    public Movie getMovieByTitle(String title) {
        return repositoryMovie.findByTitle(title);
    }
}
