package core.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder // implements builder pattern
public class Rent extends BaseEntity<Integer> {
    private int movieId;
    private int clientId;
    public Rent(int id, int movieId, int clientId){
        this.clientId = clientId;
        this.movieId = movieId;
        this.setId(id);
    }


}
