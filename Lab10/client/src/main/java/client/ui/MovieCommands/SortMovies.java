package client.ui.MovieCommands;

import core.service.ServiceClient;
import core.service.ServiceMovie;
import core.service.ServiceRent;
import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.MoviesDto;

import java.util.Scanner;

public class SortMovies implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        MoviesDto movies = restTemplate.getForObject("http://localhost:8080/api/movies_sorted", MoviesDto.class);
        System.out.println(movies);
    }
}
