package web.controller;

import core.domain.Client;
import core.domain.Movie;
import core.domain.Rent;
import core.service.ServiceRent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import web.dto.RentDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RentController {


    public static final Logger log = LoggerFactory.getLogger(RentController.class);
    @Autowired
    private ServiceRent serviceRent;

    @RequestMapping(value = "/rents", method = RequestMethod.GET, params = {"page", "size", "filterString"})
    public List<RentDto> getAllRents(@RequestParam("page") @Min(0) @NotNull int page, @RequestParam("size") @Min(0) @NotNull int size, @RequestParam("filterString") String filterString) {
        log.trace("Controller - getAllRents - entered");
        Page<Rent> moviePages = serviceRent.getAllRents(page, size, filterString);
        if (moviePages.getTotalPages() < page)
            return null;
        List<Rent> rentals = new ArrayList(moviePages.getContent());
        ArrayList<RentDto> rents = new ArrayList();
        for (Rent r : rentals) {
            rents.add(new RentDto(r.getId(), r.getMovie().getTitle(), r.getClient().getName()));
        }

        log.trace("Controller - getAllRents - rents: {}", rents);
        return rents;
    }

    @RequestMapping(value = "/rents", method = RequestMethod.POST)
    public void addRent(@RequestBody RentDto rentDto) {
        log.trace("Controller - addRent - rent: {}", rentDto);
        try {
            Movie m = serviceRent.getMovieByTitle(rentDto.getMovieTitle());
            Client c = serviceRent.getClientByName(rentDto.getClientName());
            Rent r = new Rent();
            if (m != null && c != null) {
                r.setClient(c);
                r.setMovie(m);
                serviceRent.addRent(r);
                log.trace("Controller - rent added");
            }
        } catch (Exception e) {
            log.trace("Controller - rent add failed. Error: \n {}", e.toString());
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
