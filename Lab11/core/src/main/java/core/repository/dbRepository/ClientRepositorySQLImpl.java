package core.repository.dbRepository;

import core.domain.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Component
public class ClientRepositorySQLImpl extends CustomRepositorySupport
        implements ClientRepositorySQL {

    @Override
    @Transactional
    public List<Client> findAllWithRentsAndMovies() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(org.hibernate.jpa.HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("select distinct {a.*},{b.*},{p.*} " +
                "from client a " +
                "left join rent b on a.id=b.client_id " +
                "left join movie p on b.movie_id=p.id ")
                .addEntity("a",Client.class)
                .addJoin("b", "a.rentals")
                .addJoin("p", "b.movie")
                .addEntity("a",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Client> clients = query.getResultList();
        return clients;
    }

    @Override
    public void updateClient(Client client) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        org.hibernate.Query query = session.createSQLQuery("update client a set name='" + client.getName()+"', age="+client.getAge() + " where id="+client.getId())
                .addEntity("a",Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.executeUpdate();
    }

}


