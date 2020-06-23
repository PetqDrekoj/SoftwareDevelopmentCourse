package client.ui.MovieCommands;

import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.MoviesDto;

import java.util.Scanner;

public class ListMoviesCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        MoviesDto movies = restTemplate.getForObject("http://localhost:8080/api/movies", MoviesDto.class);
        movies.getMovies().forEach(System.out::println);
    }
}
