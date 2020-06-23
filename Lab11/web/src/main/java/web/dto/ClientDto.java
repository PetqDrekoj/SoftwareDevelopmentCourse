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
public class ClientDto extends BaseDto{
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value=120, message = "Age should not be greater than 120")
    private int age;
    private List<Rent> rentals;
    public ClientDto(int id, String name, int age, List<Rent> rentals){
        this.name = name;
        this.age = age;
        this.rentals = rentals;
        this.setId(id);
    }
    public ClientDto(int id, String name, int age){
        this.name = name;
        this.age = age;
        this.setId(id);
    }

}
