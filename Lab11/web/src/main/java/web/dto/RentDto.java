package web.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder // implements builder pattern
public class RentDto extends BaseDto {
    private String movieTitle;
    private String clientName;

    public RentDto(Integer id, String movieTitle, String clientName) {
        this.setId(id);
        this.movieTitle = movieTitle;
        this.clientName = clientName;
    }
}
