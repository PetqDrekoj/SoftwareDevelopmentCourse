package client.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import core.service.ServiceClient;
import core.service.ServiceMovie;
import core.service.ServiceRent;
import client.ui.ClientCommands.AddClientCommand;
import client.ui.ClientCommands.DeleteClientCommand;
import client.ui.ClientCommands.ListClientsCommand;
import client.ui.ClientCommands.UpdateClientCommand;
import client.ui.MovieCommands.*;
import client.ui.RentCommands.AddRentCommand;
import client.ui.RentCommands.FilterMoviesIfRentedCommand;
import client.ui.RentCommands.FindMostRentedMovieCommand;
import client.ui.RentCommands.ListRentsCommand;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

@Component
public class Console {

    @Autowired
    private RestTemplate restTemplate;

    private Scanner scanner;
    private Map<Integer, Command> commands;

    public Console() {
        this.scanner = new Scanner(System.in);
        addCommands();
    }

    public void runConsole() {
        printMenu();
        IntStream.iterate(1, i -> i + 1).forEach(val -> {
            int command = scanner.nextInt();
            commands.getOrDefault(command, new WrongCommand()).execute(scanner, restTemplate);
            printMenu();
        });
    }

    public void printMenu() {
        System.out.println("0 - Exit");
        System.out.println("1 - Add movie");
        System.out.println("2 - List movies");
        System.out.println("3 - Delete movie");
        System.out.println("4 - Update movie");
        System.out.println("5 - Add client");
        System.out.println("6 - List clients");
        System.out.println("7 - Delete client");
        System.out.println("8 - Update client");
        System.out.println("9 - Add rent");
        System.out.println("10 - List rents");
        System.out.println("11 - Show movies that we're rented");
        System.out.println("12 - Show most rented movie");
        System.out.println("13 - Show sorted movies");
    }

    public void addCommands() {
        commands = new HashMap<Integer, Command>();
        commands.put(0, new ExitCommand());
        commands.put(1, new AddMovieCommand());
        commands.put(2, new ListMoviesCommand());
        commands.put(3, new DeleteMovieCommand());
        commands.put(4, new UpdateMovieCommand());
        commands.put(5, new AddClientCommand());
        commands.put(6, new ListClientsCommand());
        commands.put(7, new DeleteClientCommand());
        commands.put(8, new UpdateClientCommand());
        commands.put(9, new AddRentCommand());
        commands.put(10, new ListRentsCommand());
        commands.put(11, new FilterMoviesIfRentedCommand());
        commands.put(12, new FindMostRentedMovieCommand());
        commands.put(13, new SortMovies());
    }
}
