package core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import core.ITConfig;
import core.domain.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class MovieServiceTest {

    @Autowired
    private ServiceMovie serviceMovie;

    @Test
    public void findAll() {
        List<Movie> movies = serviceMovie.getAllMovies();
        assertEquals("there should be four students", 2, movies.size());
    }

    @Test
    public void getMovieByTitle() {
        Movie m = serviceMovie.getMovieByTitle("title1");
        assertNotNull(m);
        assertEquals("title1", m.getTitle());
    }


    @Test
    public void updateMovie() {
        Movie c = new Movie("title3", "genre3", 2000, null);
        serviceMovie.updateMovie("title2", c);
        Movie new_movie = serviceMovie.getMovieByTitle("title3");
        assertNotNull(new_movie);
        assertEquals(c.getYear(), new_movie.getYear());
        assertEquals(c.getGenre(), new_movie.getGenre());
        assertEquals(c.getTitle(), new_movie.getTitle());

        List<Movie> movies = serviceMovie.getAllMovies();
        assertEquals("there should be four students", 2, movies.size());
    }

    @Test
    public void deleteMovie() {
        List<Movie> movies = serviceMovie.getAllMovies();
        assertEquals("there should be 2 movies", 2, movies.size());

        serviceMovie.deleteMovie("title1");

        List<Movie> movies1 = serviceMovie.getAllMovies();
        assertEquals("there should be 1 movie", 1, movies1.size());
    }

    @Test
    public void addMovie(){
        List<Movie> old_movies  = this.serviceMovie.getAllMovies();
        assertEquals("there should be 2 movies", 2, old_movies.size());
        Movie m = new Movie("title3","genre",1999,null);
        serviceMovie.addMovie(m);
        Movie savedMovie = serviceMovie.getMovieByTitle("title3");
        assertNotNull(savedMovie);
        assertNotNull(savedMovie.getId());
        List<Movie> movies  = this.serviceMovie.getAllMovies();
        assertEquals("there should be 3 movies", 3, movies.size());
    }


}
