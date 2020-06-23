package core.repository.dbRepository;

import core.domain.Client;
import core.domain.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CatalogRepository<Movie, Integer>,MovieRepositoryJPQL,MovieRepositoryNative,MovieRepositoryCriteria {
    @Query("select distinct a from Movie a where a.title like '%(:title)%' ")
    @EntityGraph(value = "movieWithRentals", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllByTitleContaining(@Param("title") String name);

    @Query("select distinct a from Movie a")
    @EntityGraph(value = "movie", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllSimple();

    @Query("select distinct a from Movie a")
    @EntityGraph(value = "movieWithRentals", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllWithRentals();

    @Query("select a from Movie a where a.title = (:title) ")
    @EntityGraph(value = "movieWithRentals", type = EntityGraph.EntityGraphType.LOAD)
    Movie findByTitle(@Param("title") String title);

}
