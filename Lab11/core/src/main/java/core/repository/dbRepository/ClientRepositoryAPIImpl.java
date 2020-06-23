package core.repository.dbRepository;

import core.domain.Client;
import core.domain.Client_;
import core.domain.Rent;
import core.domain.Rent_;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClientRepositoryAPIImpl extends CustomRepositorySupport
        implements ClientRepositoryAPI {

    @Override
    public List<Client> findAllWithRentsAndMovies() {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.distinct(Boolean.TRUE);
        Root<Client> root = query.from(Client.class);
        Fetch<Client, Rent> authorBookFetch = root.fetch(Client_.rentals, JoinType.LEFT);
        authorBookFetch.fetch(Rent_.movie, JoinType.LEFT);
        List<Client> clients = entityManager.createQuery(query).getResultList();
        return clients;
    }


    @Override
    @Transactional
    public void updateClient(Client client) {
        EntityManager entityManager = getEntityManager();
        entityManager.joinTransaction();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Client> query = criteriaBuilder.createCriteriaUpdate(Client.class);
        Root<Client> root = query.from(Client.class);
        root.alias("client");
        query.set(root.get("name"), client.getName());
        query.set(root.get("age"), client.getAge());
        Predicate id = criteriaBuilder.equal(root.get("id"), client.getId());
        query.where(id);
        Query q = entityManager.createQuery(query);
        q.executeUpdate();
    }


}

