package core.domain;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "movieWithRentals",
                attributeNodes = @NamedAttributeNode(value = "rentals", subgraph = "rentWithClient"),
                subgraphs = @NamedSubgraph(name = "rentWithClient", attributeNodes = @NamedAttributeNode(value="client"))),

        @NamedEntityGraph(name = "movie"),


})


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="movie")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder // implements builder pattern
public class Movie extends BaseEntity<Integer> {

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Column(unique = true)
    private String title;
    @NotNull(message = "Genre cannot be null")
    @Size(min = 1, max = 50, message = "Genre must be between 1 and 50 characters")
    private String genre;

    @NotNull(message = "Year cannot be null")
    @Min(value = 0, message = "Year should not be less than 0")
    @Max(value=2030, message = "Year should not be greater than 2030")
    private int year;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie", fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<Rent> rentals;

}
