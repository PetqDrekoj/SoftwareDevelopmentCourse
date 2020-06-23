package springjpa.ui.MovieCommands;

import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class ListMoviesCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        serviceMovie.getAllMovies().forEach(System.out::println);
    }
}
