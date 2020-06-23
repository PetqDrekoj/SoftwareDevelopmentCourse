package springjpa.ui.ClientCommands;

import springjpa.domain.validators.MovieProjectException;
import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class DeleteClientCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        try {
            serviceClient.deleteClient(id);
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
