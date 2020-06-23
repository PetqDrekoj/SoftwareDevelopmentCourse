package core.repository.dbRepository;

import core.domain.Client;
import core.domain.Client_;
import core.domain.Rent;
import core.domain.Rent_;
import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

//public class ClientRepositoryImpl extends CustomRepositorySupport implements ClientRepositoryCustom{

//    @Override
//    @Transactional
//    public Client addClientJPQL(Client client) {
//        EntityManager entityManager = getEntityManager();
//        entityManager.joinTransaction();
//        String sqlString= "INSERT INTO Client (id,name,age) VALUES (" +client.getId()+", '"+ client.getName()+"', "+ client.getAge()+")";
//        Query query = entityManager.createNativeQuery(sqlString);
//        int result = query.executeUpdate();
//        return client;
//    }

//    @Override
//    @Transactional
//    public void updateClientJPQL(Client client){
//        EntityManager entityManager = getEntityManager();
//        entityManager.joinTransaction();
//        String srt = "update Client a set a.name='"+client.getName()+"', a.age="+client.getAge()+ " where a.id="+client.getId();
//        Query query = entityManager.createQuery(srt);
//        query.executeUpdate();
//    }

//    @Override
//    @Transactional
//    public void updateClientCriteriaAPI(Client client) {
//        EntityManager entityManager = getEntityManager();
//        entityManager.joinTransaction();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaUpdate<Client> query = criteriaBuilder.createCriteriaUpdate(Client.class);
//        Root<Client> root = query.from(Client.class);
//        root.alias("client");
//        query.set(root.get("name"), client.getName());
//        query.set(root.get("age"), client.getAge());
//        Predicate id = criteriaBuilder.equal(root.get("id"), client.getId());
//        query.where(id);
//        Query q = entityManager.createQuery(query);
//        q.executeUpdate();
//    }

//    @Override
//    public void updateClientSQL(Client client) {
//        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
//        Session session = hibernateEntityManager.getSession();
//        org.hibernate.Query query = session.createSQLQuery("update client a set name='" + client.getName()+"', age="+client.getAge() + " where id="+client.getId())
//                .addEntity("a",Client.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        query.executeUpdate();
//    }





//    @Override
//    public List<Client> findAllWithRentsJPQL() {
//        EntityManager entityManager = getEntityManager();
//        Query query = entityManager.createQuery(
//                "select distinct a from Client a " +
//                        "left join fetch a.rentals b " +
//                        "left join fetch b.movie");
//        return (List<Client>) query.getResultList();
//    }

//    @Override
//    public List<Client> findAllWithRentsAndMoviesCriteriaAPI() {
//        EntityManager entityManager = getEntityManager();
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
//        query.distinct(Boolean.TRUE);
//        Root<Client> root = query.from(Client.class);
//        Fetch<Client, Rent> authorBookFetch = root.fetch(Client_.rentals, JoinType.LEFT);
//        authorBookFetch.fetch(Rent_.movie, JoinType.LEFT);
//        List<Client> clients = entityManager.createQuery(query).getResultList();
//        return clients;
//    }

//    @Override
//    @Transactional
//    public List<Client> findAllWithRentsAndMoviesSQL() {
//        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(org.hibernate.jpa.HibernateEntityManager.class);
//        Session session = hibernateEntityManager.getSession();
//        Query query = session.createSQLQuery("select distinct {a.*},{b.*},{p.*} " +
//                "from client a " +
//                "left join rent b on a.id=b.client_id " +
//                "left join movie p on b.movie_id=p.id ")
//                .addEntity("a",Client.class)
//                .addJoin("b", "a.rentals")
//                .addJoin("p", "b.movie")
//                .addEntity("a",Client.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        List<Client> clients = query.getResultList();
//        return clients;
//    }
//}
