package client.ui;

import core.service.ServiceClient;
import core.service.ServiceMovie;
import core.service.ServiceRent;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class WrongCommand implements Command {
    
    @Override
    public void execute(Scanner scanner, RestTemplate restTemplate) {
        System.out.println("Wrong command");
    }
}
