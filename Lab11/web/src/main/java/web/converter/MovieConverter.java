package web.converter;

import core.domain.Movie;
import org.springframework.stereotype.Component;
import web.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        return new Movie(dto.getTitle(),dto.getGenre(),dto.getYear(),null);
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        return new MovieDto(movie.getId(),movie.getTitle(),movie.getGenre(),movie.getYear());
    }
}
