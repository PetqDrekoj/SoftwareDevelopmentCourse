package core.repository.dbRepository;

import core.domain.Client;
import core.domain.Movie;

import java.util.List;

public interface MovieRepositoryCustom {

    void updateMovie(Movie movie);
    List<Movie> findAllWithRentsAndClients();
}
