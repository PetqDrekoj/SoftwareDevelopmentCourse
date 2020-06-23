package client.ui.MovieCommands;

import core.domain.Movie;
import core.domain.validators.MovieProjectException;
import core.service.ServiceClient;
import core.service.ServiceMovie;
import core.service.ServiceRent;
import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.MovieDto;

import java.util.Scanner;

public class UpdateMovieCommand implements Command {

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
            MovieDto movie = new MovieDto(title, genre,year);
            movie.setId(id);
            restTemplate.put("http://localhost:8080/api/movies/{id}", movie, id);
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
