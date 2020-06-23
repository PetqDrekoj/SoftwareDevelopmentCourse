package springjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springjpa.domain.Client;
import springjpa.domain.Movie;
import springjpa.domain.validators.MovieProjectException;
import springjpa.domain.validators.Validator;
import springjpa.domain.validators.ValidatorException;
import springjpa.repository.dbRepository.ClientRepository;
import springjpa.repository.dbRepository.MovieRepository;
import springjpa.repository.dbRepository.RentRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ServiceClient {

    public static final Logger log = LoggerFactory.getLogger(ServiceClient.class);
    @Autowired
    private MovieRepository repositoryMovie;
    @Autowired
    private ClientRepository repositoryClient;
    @Autowired
    private RentRepository repositoryRent;
    @Autowired
    private Validator<Client> validator;

    /**
     * Adds a client in the service.
     *
     * @param client Client
     * @throws MovieProjectException if client is <code>null</code> | if client is not valid | if couldn't save to text file | if couldn't save to xml file
     */
    public void addClient(Client client) throws MovieProjectException {
        log.trace("Service - addClient --- method entered");
        validator.validate(client);
        Client result = repositoryClient.findById(client.getId()).orElse(client);
        Optional<Boolean> clientValidator = Optional.of(result.equals(client));
        log.trace("Service - addClient: result={}", result);
        clientValidator.filter(v -> v).orElseThrow(() -> new MovieProjectException("id is used"));
        repositoryClient.save(client);


    }


    /**
     * Removes a client from the service given its id.
     *
     * @param id Integer
     * @throws MovieProjectException if id is <code>null</code>
     */
    public void deleteClient(Integer id) throws MovieProjectException {
        log.trace("Service - deleteClient --- method entered");
        try {
            this.repositoryClient.findById(id).orElseThrow(() -> new ValidatorException("id not valid"));
            this.repositoryClient.deleteById(id);
            this.repositoryRent.findAll().forEach(rent -> {
                if (rent.getClientId() == id) this.repositoryRent.deleteById(rent.getId());
            });
            log.trace("Service - deleteClient --- delete successful, id = {}", id);
        } catch (Exception e) {
            throw new MovieProjectException(e.getMessage(), e);
        }
    }

    /**
     * Updates a client in the service.
     *
     * @param client Client
     * @throws MovieProjectException if client is <code>null</code> | if client is not valid. | if couldn't update the text file | if couldn't update the xml file
     */
    public void updateClient(Client client) throws MovieProjectException {
        log.trace("Service - updateClient --- method entered");
        validator.validate(client);
        repositoryClient.findById(client.getId()).orElseThrow(() -> new MovieProjectException("id not existing"));
        Client result = repositoryClient.save(client);
        log.trace("Service - updateClient --- client updated successfully. \nresult = {}", result);
    }

    /**
     * Returns the set of clients.
     *
     * @return Set of clients
     */
    public Set<Client> getAllClients() {
        log.trace("Service - getAllClients --- method entered");

        Set<Client> clients = new HashSet<>(repositoryClient.findAll());

        log.trace("Service - getAllClients --- movies = {}", clients);
        return clients;
    }

}
