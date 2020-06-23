package springjpa.ui.MovieCommands;

import springjpa.domain.Movie;
import springjpa.domain.validators.MovieProjectException;
import springjpa.service.ServiceClient;
import springjpa.service.ServiceMovie;
import springjpa.service.ServiceRent;
import springjpa.ui.Command;

import java.util.Scanner;

public class AddMovieCommand implements Command {

    @Override
    public void execute(Scanner scanner, ServiceMovie serviceMovie, ServiceClient serviceClient, ServiceRent serviceRent) {
        System.out.println("Id: ");
        int id = scanner.nextInt();
        System.out.println("Title: ");
        String title = scanner.next();
        System.out.println("Genre: ");
        String genre = scanner.next();
        System.out.println("Year: ");
        int year = scanner.nextInt();
        try {
            serviceMovie.addMovie(new Movie(id, title, genre, year));
        } catch (MovieProjectException e) {
            System.out.println(e.toString());
        }
    }
}
