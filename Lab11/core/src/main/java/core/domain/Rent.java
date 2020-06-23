package core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="rent")
@Builder // implements builder pattern
public class Rent extends BaseEntity<Integer> {

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Rent(Integer id, Movie movie, Client client){
        this.setId(id);
        this.movie = movie;
        this.client = client;
    }



}
