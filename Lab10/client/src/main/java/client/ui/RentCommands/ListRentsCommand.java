package client.ui.RentCommands;

import client.ui.Command;
import org.springframework.web.client.RestTemplate;
import web.dto.RentsDto;

import java.util.Scanner;

public class ListRentsCommand implements Command {

    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        RentsDto rents = restTemplate.getForObject("http://localhost:8080/api/rents", RentsDto.class);
        rents.getRents().forEach(System.out::println);
    }
}
