package org.spring_project.calendar.User;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    public boolean makeNewUser(UserDTO signUp){
        Optional<App_User> optionalUser =  userRepository.findByUsername(signUp.username());
        if(optionalUser.isPresent()){
            return false;
        }
        App_User user = new App_User(signUp);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
