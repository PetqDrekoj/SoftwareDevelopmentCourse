package client.ui.MovieCommands;

import client.ui.Command;
import core.domain.validators.MovieProjectException;
import org.springframework.web.client.RestTemplate;
import web.dto.MovieDto;

import java.util.Scanner;

public class AddMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Title: ");
        String title = scanner.next();
        System.out.println("Genre: ");
        String genre = scanner.next();
        System.out.println("Year: ");
        int year = scanner.nextInt();
        try {
            MovieDto movie = new MovieDto(title, genre, year);
            movie.setId(id);
            restTemplate.postForObject("http://localhost:8080/api/movies/", movie, MovieDto.class);
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
