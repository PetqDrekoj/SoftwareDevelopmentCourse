package client.ui.ClientCommands;

import client.ui.Command;
import core.domain.validators.MovieProjectException;
import org.springframework.web.client.RestTemplate;
import web.dto.ClientDto;

import java.util.Scanner;

public class AddClientCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        try {
            ClientDto client = new ClientDto(id,name, age);
            restTemplate.postForObject("http://localhost:8080/api/clients", client, ClientDto.class);
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
