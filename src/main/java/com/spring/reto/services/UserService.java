package com.spring.reto.services;

import com.spring.reto.dto.LoginDTO;
import com.spring.reto.dto.UserDTO;
import com.spring.reto.responses.LoginMessage;
import com.spring.reto.entity.User;
import com.spring.reto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDTO userDTO){
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserName(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        return userRepository.save(user);
    }

    public LoginMessage loginUser(LoginDTO loginDTO) {
        String responseMessage = "";
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String loginPassword = loginDTO.getPassword();
            String serverEncodedPassword = user.getPassword();
            Boolean isPaswordCorrect = passwordEncoder.matches(loginPassword, serverEncodedPassword);
            if (isPaswordCorrect){
                Optional<User> userOptional = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(),serverEncodedPassword);
                if (userOptional.isPresent()){
                    return new LoginMessage("Login Success", true);
                }
                return new LoginMessage("Login Failed", false);
            }
            return new LoginMessage("Password Not Match", false);
        }
        return new LoginMessage("Email not exist", false);
    }
}
