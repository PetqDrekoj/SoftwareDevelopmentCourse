package web.controller;

import core.domain.Client;
import core.domain.User;
import core.service.ServiceClient;
import core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import web.converter.ClientConverter;
import web.dto.ClientDto;
import web.dto.UserCredentialsDto;
import web.security.CatalogUserDetailsService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    public static final Logger log = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientConverter clientConverter;




    @RequestMapping(value = "/login", method = RequestMethod.POST)
        public UserCredentialsDto login(@RequestBody UserCredentialsDto user){
        User u = userService.getUserByUserName(user.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(user.getPassword(), u.getPassword())) return user;
        else return new UserCredentialsDto("","");
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientDto> getAllClients() {
        log.trace("Controller - getAllClients - entered");
        List<Client> clientPage = serviceClient.getAllClients();
        List<ClientDto> clients = new ArrayList(clientConverter.convertModelsToDtos(clientPage));
        log.trace("Controller - getAllClients - clients: \n {}", clients);
        return clients;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ClientDto addClient(@RequestBody @Valid ClientDto clientDto) {
        log.trace("Controller - addClient - {}", clientDto);
        Client c = clientConverter.convertDtoToModel(clientDto);
        try {
            serviceClient.addClient(c);
            log.trace("Controller - client saved");
        } catch (Exception e) {
            log.trace("Controller - client save failed. Error: \n {}", e.toString());
        }
        return clientConverter.convertModelToDto(c);
    }

    @RequestMapping(value = "/clients/{client_name}", method = RequestMethod.PUT)
    void updateClient(@PathVariable @NotNull String client_name,
                      @RequestBody @Valid ClientDto clientDto) {
        log.trace("Controller - updateClient - id: {} client: {}", client_name, clientDto);
        try {
            serviceClient.updateClient(client_name, clientConverter.convertDtoToModel(clientDto));
            log.trace("Controller - client updated");
        } catch (Exception e) {
            log.trace("Controller - client update failed. Error: \n {}", e.toString());
        }
    }

    @RequestMapping(value = "/clients/{client_name}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable @NotNull String client_name) {
        log.trace("Controller - deleteClient - id: {} ", client_name);
        try {
            serviceClient.deleteClient(client_name);
            log.trace("Controller - client deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.trace("Controller - client delete failed. Error: \n {}", e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
