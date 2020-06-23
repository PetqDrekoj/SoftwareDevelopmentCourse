package client.ui.RentCommands;

import client.ui.Command;
import core.domain.validators.MovieProjectException;
import org.springframework.web.client.RestTemplate;
import web.dto.RentDto;

import java.util.Scanner;

public class AddRentCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        System.out.println("Rent Id: ");
        int rentId = scanner.nextInt();
        System.out.println("Movie Id: ");
        int movieId = scanner.nextInt();
        System.out.println("Client Id: ");
        int clientId = scanner.nextInt();
        try {
            RentDto rent = new RentDto(movieId, clientId);
            rent.setId(rentId);
            restTemplate.postForObject("http://localhost:8080/api/rents", rent, RentDto.class);
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
