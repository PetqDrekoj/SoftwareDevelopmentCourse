package web.converter;

import core.domain.Rent;
import org.springframework.stereotype.Component;
import web.dto.RentDto;

@Component
public class RentConverter extends BaseConverter<Rent, RentDto> {
    @Override
    public Rent convertDtoToModel(RentDto dto) {
        return new Rent(dto.getId(), dto.getMovieId(), dto.getClientId());
    }

    @Override
    public RentDto convertModelToDto(Rent rent) {
        return new RentDto(rent.getId(), rent.getMovieId(), rent.getClientId());
    }
}
