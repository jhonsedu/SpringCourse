package com.spring.reto.services;

import com.spring.reto.dto.LoginDTO;
import com.spring.reto.dto.UserDTO;
import com.spring.reto.responses.Message;
import com.spring.reto.entity.User;
import com.spring.reto.repositories.UserRepository;
import com.spring.reto.utils.EmailValidator;
import com.spring.reto.utils.PasswordValidator;
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

    public Message saveUser(UserDTO userDTO){
        try {
            User user = new User(
                    userDTO.getUserId(),
                    userDTO.getUserName(),
                    userDTO.getEmail(),
                    this.passwordEncoder.encode(userDTO.getPassword())
            );

            if (!EmailValidator.isValidEmail(userDTO.getEmail())) {
                return new Message("Invalid email format", false);
            }

            if (userRepository.findByEmail(userDTO.getEmail()) != null){
                return new Message("Email Already Exist", false);
            }

            if (!PasswordValidator.isValidPassword(userDTO.getPassword())) {
                return new Message("Invalid password format", false);
            }

            if (userRepository.findByUserName(userDTO.getUserName()) != null){
                return new Message("Username already taken", false);
            }

            userRepository.save(user);
            return new Message("User Created Successfully", true);
        } catch (Exception e) {
            return new Message(e.getMessage(), false);
        }
    }

    public Message loginUser(LoginDTO loginDTO) {
        String responseMessage = "";
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null) {
            String loginPassword = loginDTO.getPassword();
            String serverEncodedPassword = user.getPassword();
            boolean isPasswordCorrect = passwordEncoder.matches(loginPassword, serverEncodedPassword);
            if (isPasswordCorrect){
                Optional<User> userOptional = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(),serverEncodedPassword);
                if (userOptional.isPresent()){
                    return new Message("Login Success", true);
                }
                return new Message("Login Failed", false);
            }
            return new Message("Password Not Match", false);
        }
        return new Message("Email not exist", false);
    }
}
