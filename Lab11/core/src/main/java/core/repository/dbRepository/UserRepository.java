package core.repository.dbRepository;

import core.domain.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends CatalogRepository<User, Long> {

    @Query("select u from User u where u.user_Name=?1")
    User getUserByUserName(String username);
}
