package client.ui.RentCommands;

import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.StringsDto;

import java.util.Scanner;

public class FilterMoviesIfRentedCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        StringsDto movies = restTemplate.getForObject("http://localhost:8080/api/filterrents", StringsDto.class);
        movies.getMovies().forEach(System.out::println);
    }
}
