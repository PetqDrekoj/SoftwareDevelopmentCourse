package core.repository.dbRepository;

import core.domain.Client;
import core.domain.Movie;

import java.util.List;

public interface ClientRepositoryCustom {

    void updateClientJPQL(Client client);
    void updateClientCriteriaAPI(Client client);
    void updateClientSQL(Client client);


    List<Client> findAllWithRentsJPQL();
    List<Client> findAllWithRentsAndMoviesCriteriaAPI();
    List<Client> findAllWithRentsAndMoviesSQL();
}
