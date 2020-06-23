package springjpa.ui.RentCommands;

import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class ListRentsCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        serviceRent.getAllRents().forEach(System.out::println);
    }
}
