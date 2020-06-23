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
public class Movie extends BaseEntity<Integer> {
    private String title;
    private String genre;
    private int year;

    public Movie(int id, String title, String genre, int year){
        this.genre = genre;
        this.title = title;
        this.year = year;
        this.setId(id);
    }
}
