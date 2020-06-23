package springjpa.ui;

import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;

import java.util.Scanner;

public class WrongCommand implements Command {
    
    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        System.out.println("Wrong command");
    }
}
