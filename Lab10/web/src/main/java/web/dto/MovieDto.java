package web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder // implements builder pattern
public class MovieDto extends BaseDto{
    private String title;
    private String genre;
    private int year;
    public MovieDto(int id, String title, String genre, int year){
        this.genre = genre;
        this.title = title;
        this.year = year;
        this.setId(id);
    }
}