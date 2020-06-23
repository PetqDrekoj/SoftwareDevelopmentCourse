package core.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithRentals",
                attributeNodes = @NamedAttributeNode(value = "rentals")),

        @NamedEntityGraph(name = "client")
})

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "client")
@Builder // implements builder pattern
public class Client extends BaseEntity<Integer> {

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    @Column(unique = true)
    private String name;
    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 120, message = "Age should not be greater than 120")
    private int age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<Rent> rentals;

    public Client(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.setId(id);
    }

    public Client(int id, String name, int age, List<Rent> rentals) {
        this.name = name;
        this.age = age;
        this.setId(id);
        this.rentals = rentals;
    }
}
