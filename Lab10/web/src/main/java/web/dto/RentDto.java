package web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class RentDto extends BaseDto{
    private int movieId;
    private int clientId;
    public RentDto(int id, int movieId, int clientId){
        this.clientId = clientId;
        this.movieId = movieId;
        this.setId(id);
    }
}
