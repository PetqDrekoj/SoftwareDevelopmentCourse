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
public class Client extends BaseEntity<Integer> {
    private String name;
    private int age;

    public Client(int id, String name, int age){
        this.name = name;
        this.age = age;
        this.setId(id);
    }
}
