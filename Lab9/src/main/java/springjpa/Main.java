package springjpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springjpa.ui.Console;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("hello");

        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext("springjpa");
        config.getBean(Console.class).runConsole();

        System.out.println("bye");
    }
}
