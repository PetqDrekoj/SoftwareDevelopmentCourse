package client;

import client.ui.Console;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import web.dto.MovieDto;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("client.config");


        Console console = context.getBean(Console.class);
        console.runConsole();


        System.out.println("bye ");
    }
}
