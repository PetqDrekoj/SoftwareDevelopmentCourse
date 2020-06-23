package client.ui.ClientCommands;

import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.ClientsDto;

import java.util.Scanner;

public class ListClientsCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        ClientsDto clients = restTemplate.getForObject("http://localhost:8080/api/clients", ClientsDto.class);
        clients.getClients().forEach(System.out::println);
    }
}
