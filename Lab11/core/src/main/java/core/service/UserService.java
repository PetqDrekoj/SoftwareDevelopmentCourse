package core.service;

import core.domain.User;
import core.repository.dbRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;


    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    public boolean checkCredentials(String username, String password) {
        User u = userRepository.getUserByUserName(username);
        return u != null && u.getPassword().equals(password);
    }
}
