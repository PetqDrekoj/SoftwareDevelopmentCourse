package core.repository.dbRepository;


import core.domain.Client;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import java.util.List;
@Primary
@Order(1)
public interface ClientRepositoryAPI {

    List<Client> findAllWithRentsAndMovies();

    void updateClient(Client client);
}
