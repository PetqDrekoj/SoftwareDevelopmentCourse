package client.ui.RentCommands;

import client.ui.Command;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class FindMostRentedMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        String movie = restTemplate.getForObject("http://localhost:8080/api/reportrents", String.class);
        System.out.println(movie);
    }
}
