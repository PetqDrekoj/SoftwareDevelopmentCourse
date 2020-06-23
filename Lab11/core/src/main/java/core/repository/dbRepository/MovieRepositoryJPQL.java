package core.repository.dbRepository;

import core.domain.Movie;

import java.util.List;

public interface MovieRepositoryJPQL {
    void updateMovie(Movie movie);
    List<Movie> findAllWithRentsAndClients();
}
