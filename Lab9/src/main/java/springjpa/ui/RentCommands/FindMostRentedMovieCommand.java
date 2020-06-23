package springjpa.ui.RentCommands;

import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class FindMostRentedMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        System.out.println("The most rented movie is " + serviceRent.findMostRentedMovie());
    }
}
