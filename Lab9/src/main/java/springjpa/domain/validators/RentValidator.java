package springjpa.domain.validators;

import org.springframework.stereotype.Component;
import springjpa.domain.Rent;

@Component
public class RentValidator implements Validator<Rent> {
    @Override
    public void validate(Rent entity) throws ValidatorException {

    }
}
