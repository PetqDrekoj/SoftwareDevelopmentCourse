package core.repository.dbRepository;

import core.domain.Movie;
import core.domain.Movie_;
import core.domain.Rent;
import core.domain.Rent_;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;
@Component
@Order(1)
@Lazy
public class MovieRepositoryCriteriaImpl extends CustomRepositorySupport implements MovieRepositoryCriteria {

    @Override
    @Transactional
    public void updateMovie(Movie movie) {
        EntityManager entityManager = getEntityManager();
        entityManager.joinTransaction();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Movie> query = criteriaBuilder.createCriteriaUpdate(Movie.class);
        Root<Movie> root = query.from(Movie.class);
        root.alias("movie");
        query.set(root.get("title"), movie.getTitle());
        query.set(root.get("genre"), movie.getGenre());
        query.set(root.get("year"), movie.getYear());
        Predicate id = criteriaBuilder.equal(root.get("id"), movie.getId());
        query.where(id);
        Query q = entityManager.createQuery(query);
        q.executeUpdate();
    }

    @Override
    public List<Movie> findAllWithRentsAndClients() {
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        query.distinct(Boolean.TRUE);
        Root<Movie> root = query.from(Movie.class);
        Fetch<Movie, Rent> movieRentFetch = root.fetch(Movie_.rentals, JoinType.LEFT);
        movieRentFetch.fetch(Rent_.client, JoinType.LEFT);
        List<Movie> movies = entityManager.createQuery(query).getResultList();
        return movies;
    }
}
