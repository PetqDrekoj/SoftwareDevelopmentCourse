package core.repository.dbRepository;

import core.domain.Client;
import java.util.List;

public interface ClientRepositoryJPQL {
    List<Client> findAllWithRentsAndMovies();

    void updateClient(Client client);
}
