package springjpa.ui.ClientCommands;

import springjpa.domain.Client;
import springjpa.domain.validators.MovieProjectException;
import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class UpdateClientCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        try{
            serviceClient.updateClient(new Client(id,name,age));
        }
        catch(MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
