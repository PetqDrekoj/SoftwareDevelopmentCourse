package core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor  // generate no args constructor
@AllArgsConstructor // generate all args constructor
@Data  // generates getters, setters and to_string + hash
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    private ID id;
}
