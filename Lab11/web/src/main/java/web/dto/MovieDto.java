package web.dto;

import core.domain.Rent;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder // implements builder pattern
public class MovieDto extends BaseDto{
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;
    @NotNull(message = "Genre cannot be null")
    @Size(min = 1, max = 50, message = "Genre must be between 1 and 50 characters")
    private String genre;

    @NotNull(message = "Year cannot be null")
    @Min(value = 0, message = "Year should not be less than 0")
    @Max(value=2030, message = "Year should not be greater than 2030")
    private int year;


    private List<Rent> rentals;

    public MovieDto(int id, String title, String genre, int year, List<Rent> rentals){
        this.genre = genre;
        this.title = title;
        this.year = year;
        this.rentals = rentals;
        this.setId(id);
    }

    public MovieDto(int id, String title, String genre, int year){
        this.genre = genre;
        this.title = title;
        this.year = year;
        this.setId(id);
    }
}
