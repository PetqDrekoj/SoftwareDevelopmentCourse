package core.repository.dbRepository;

import core.domain.Client;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class ClientRepositoryJPQLImpl extends CustomRepositorySupport
        implements ClientRepositoryJPQL {

    @Override
    public List<Client> findAllWithRentsAndMovies() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct a from Client a " +
                        "left join fetch a.rentals b " +
                        "left join fetch b.movie");
        return (List<Client>) query.getResultList();
    }

    @Override
    @Transactional
    public void updateClient(Client client) {
        EntityManager entityManager = getEntityManager();
        entityManager.joinTransaction();
        String srt = "update Client a set a.name='" + client.getName() + "', a.age=" + client.getAge() + " where a.id=" + client.getId();
        Query query = entityManager.createQuery(srt);
        query.executeUpdate();
    }
}
