package web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder // implements builder pattern
public class ClientDto extends BaseDto{
    private String name;
    private int age;
    public ClientDto(int id, String name, int age){
        this.name = name;
        this.age = age;
        this.setId(id);
    }
}
