package client.ui.ClientCommands;

import core.domain.Client;
import core.domain.validators.MovieProjectException;
import core.service.ServiceClient;
import core.service.ServiceMovie;
import core.service.ServiceRent;
import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.ClientDto;

import java.util.Scanner;

public class UpdateClientCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        try{
            ClientDto client = new ClientDto(name, age);
            client.setId(id);
            restTemplate.put("http://localhost:8080/api/clients/{id}",client, id);
        }
        catch(MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
