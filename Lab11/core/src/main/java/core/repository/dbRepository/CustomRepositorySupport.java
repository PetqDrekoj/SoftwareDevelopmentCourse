package core.repository.dbRepository;

import lombok.Getter;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
public abstract class CustomRepositorySupport {
    @PersistenceContext
    private EntityManager entityManager;

}
