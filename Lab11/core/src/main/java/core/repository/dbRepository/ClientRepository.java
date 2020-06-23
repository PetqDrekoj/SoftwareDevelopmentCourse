package core.repository.dbRepository;

import core.domain.Client;
import core.domain.Movie;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends CatalogRepository<Client, Integer>, ClientRepositoryJPQL, ClientRepositoryAPI, ClientRepositorySQL {

    @Query("select distinct a from Client a")
    @EntityGraph(value = "clientWithRentals", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithRentals();

    @Query("select distinct a from Client a")
    @EntityGraph(value = "client", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllSimple();

    @Query("select distinct a from Client a where a.name = (:name) ")
    @EntityGraph(value = "clientWithRentals", type = EntityGraph.EntityGraphType.LOAD)
    Client findByName(@Param("name") String name);



}
