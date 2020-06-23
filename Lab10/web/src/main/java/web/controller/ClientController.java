package web.controller;

import core.service.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.ClientConverter;
import web.dto.ClientDto;
import web.dto.ClientsDto;

@RestController
public class ClientController {

    public static final Logger log = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ClientsDto getAllClients() {
        log.trace("Controller - getAllClients - entered");
        ClientsDto clients = new ClientsDto(clientConverter.convertModelsToDtos(serviceClient.getAllClients()));
        log.trace("Controller - getAllClients - clients: \n {}", clients);
        return clients;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public void addClient(@RequestBody ClientDto clientDto) {
        log.trace("Controller - addClient - {}", clientDto);
        try {
            serviceClient.addClient(clientConverter.convertDtoToModel(clientDto));
            log.trace("Controller - client saved");
        } catch (Exception e) {
            log.trace("Controller - client save failed. Error: \n {}", e.toString());
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    void updateClient(@PathVariable Integer id,
                      @RequestBody ClientDto clientDto) {
        log.trace("Controller - updateClient - id: {} client: {}", id, clientDto);
        try {
            serviceClient.updateClient(clientConverter.convertDtoToModel(clientDto));
            log.trace("Controller - client updated");
        } catch (Exception e) {
            log.trace("Controller - client update failed. Error: \n {}", e.toString());
        }
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Integer id) {
        log.trace("Controller - deleteClient - id: {} ", id);
        try {
            serviceClient.deleteClient(id);
            log.trace("Controller - client deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.trace("Controller - client delete failed. Error: \n {}", e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
