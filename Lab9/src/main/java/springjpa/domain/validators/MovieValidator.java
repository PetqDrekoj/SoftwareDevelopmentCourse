package springjpa.domain.validators;


import org.springframework.stereotype.Component;
import springjpa.domain.Movie;

import java.util.stream.IntStream;

@Component
public class MovieValidator implements Validator<Movie> {
    @Override
    public void validate(Movie entity) throws ValidatorException {
        entity.getTitle().chars().findAny().orElseThrow(() -> new ValidatorException("The title cannot be empty"));
        entity.getGenre().chars().findAny().orElseThrow(() -> new ValidatorException("The genre cannot be empty"));
        IntStream.of(entity.getYear()).filter(e -> e > 1800 && e < 2021).findAny().orElseThrow(() -> new ValidatorException("The year of the release is incorrect!"));
    }
}
