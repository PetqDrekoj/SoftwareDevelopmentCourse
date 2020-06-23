package client.ui.MovieCommands;

import core.domain.validators.MovieProjectException;
import core.service.ServiceClient;
import core.service.ServiceMovie;
import core.service.ServiceRent;
import client.ui.Command;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class DeleteMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        try{
            restTemplate.delete("http://localhost:8080/api/movies/{id}", id);
        }
        catch(MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
