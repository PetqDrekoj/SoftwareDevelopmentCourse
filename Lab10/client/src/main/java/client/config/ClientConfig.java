package client.config;

import client.ui.Console;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by radu.
 */
@Configuration
public class ClientConfig {

    @Bean
    Console console() { return new Console();}

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
