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

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceClient {

    public static final Logger log = LoggerFactory.getLogger(ServiceClient.class);
    @Autowired
    private MovieRepository repositoryMovie;
    @Autowired
    private ClientRepository repositoryClient;

    @Transactional
    public void addClient(Client client) throws MovieProjectException {
        log.trace("Service - addClient --- method entered");
        {
            try {
                int id = repositoryClient.findAllSimple().stream().map(BaseEntity::getId).mapToInt(v -> v).max().orElse(0);
                client.setId(id + 1);
                repositoryClient.save(client);
            } catch (Exception e) {
                throw new MovieProjectException(e.toString());
            }
        }
    }


    @Transactional
    public void deleteClient(String client_name) throws MovieProjectException {
        log.trace("deleteMovie - method entered: client_name={}", client_name);
        Client client = this.getClientByName(client_name);
        if (client != null) {
            for (Rent rent : client.getRentals()) {
                Movie movie = rent.getMovie();
                rent.setClient(null);
                rent.setMovie(null);
                Movie m = this.getMovieByTitle(movie.getTitle());
                if (m != null) {
                    List<Rent> rents = m.getRentals();
                    rents.remove(rent);
                    m.setRentals(rents);
                }
            }
            repositoryClient.deleteById(client.getId());
            log.trace("deleteMovie - method finished");
        }
    }

    @Transactional
    public void updateClient(String old_name, Client client) throws MovieProjectException {
        log.trace("Service - updateClient --- method entered");

        Client c = this.getClientByName(old_name);
        if (c == null) throw new MovieProjectException("no client with this name");
        client.setId(c.getId());
        repositoryClient.updateClient(client);
        log.trace("Service - updateClient --- client updated successfully. \nresult = {}", c);

    }

    public Client getClientByName(String name) {
        return repositoryClient.findAll().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }


    public List<Client> getAllClients() {
        return this.repositoryClient.findAllWithRentsAndMovies();

    }

    public Movie getMovieByTitle(String title) {
        return repositoryMovie.findAll().stream().filter(c -> c.getTitle().equals(title)).findFirst().orElse(null);
    }

}
