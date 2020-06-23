package springjpa.ui.RentCommands;

import springjpa.domain.Rent;
import springjpa.domain.validators.MovieProjectException;
import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class AddRentCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        System.out.println("Rent Id: ");
        int rentId = scanner.nextInt();
        System.out.println("Movie Id: ");
        int movieId = scanner.nextInt();
        System.out.println("Client Id: ");
        int clientId = scanner.nextInt();
        try{
            serviceRent.addRent(new Rent(rentId, movieId,clientId));
        }
        catch(MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
