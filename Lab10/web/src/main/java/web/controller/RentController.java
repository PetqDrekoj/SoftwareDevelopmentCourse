package web.controller;

import core.service.ServiceRent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.converter.RentConverter;
import web.dto.RentDto;
import web.dto.RentsDto;
import web.dto.StringsDto;

@RestController
public class RentController {


    public static final Logger log = LoggerFactory.getLogger(RentController.class);
    @Autowired
    private ServiceRent serviceRent;

    @Autowired
    private RentConverter rentConverter;

    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    public RentsDto getAllRents() {
        log.trace("Controller - getAllRents - entered");
        RentsDto rents = new RentsDto(rentConverter.convertModelsToDtos(serviceRent.getAllRents()));
        log.trace("Controller - getAllRents - rents: {}", rents);
        return rents;
    }

    @RequestMapping(value = "/rents", method = RequestMethod.POST)
    public void addRent(@RequestBody RentDto rentDto) {
        log.trace("Controller - addRent - rent: {}", rentDto);
        try {
            serviceRent.addRent(rentConverter.convertDtoToModel(rentDto));
            log.trace("Controller - rent added");
        } catch (Exception e) {
            log.trace("Controller - rent add failed. Error: \n {}", e.toString());
        }
    }

    @RequestMapping(value = "/filterrents", method = RequestMethod.GET)
    public StringsDto filterMoviesIfRented() {
        log.trace("Controller - filterMoviesIfRented");
        StringsDto movies = new StringsDto(serviceRent.filterMoviesIfRented());
        log.trace("Controller - filterMoviesIfRented. Movies: \n {}", movies);
        return movies;
    }

    @RequestMapping(value = "/reportrents", method = RequestMethod.GET)
    public String findMostRentedMovie() {
        log.trace("Controller - findMostRentedMovie");
        String movie = serviceRent.findMostRentedMovie();
        log.trace("Controller - findMostRentedMovie. Movie: {}", movie);
        return movie;
    }

}
