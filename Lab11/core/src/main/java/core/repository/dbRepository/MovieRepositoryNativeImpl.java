package core.repository.dbRepository;

import core.domain.Movie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Order(2)
@Lazy
public class MovieRepositoryNativeImpl extends CustomRepositorySupport implements MovieRepositoryNative {
    @Override
    public void updateMovie(Movie movie) {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        org.hibernate.Query query = session.createSQLQuery("update movie a set title='" + movie.getTitle() + "', genre='" + movie.getGenre() + "', year=" + movie.getYear() + " where id=" + movie.getId())
                .addEntity("a", Movie.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<Movie> findAllWithRentsAndClients() {
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        org.hibernate.Query query = session.createSQLQuery("select distinct {a.*},{b.*},{p.*} " +
                "from movie a " +
                "left join rent b on a.id=b.movie_id " +
                "left join client p on b.client_id=p.id ")
                .addEntity("a", Movie.class)
                .addJoin("b", "a.rentals")
                .addJoin("p", "b.client")
                .addEntity("a", Movie.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Movie> movies = query.getResultList();
        return movies;
    }
}
