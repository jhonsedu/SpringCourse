package com.spring.reto.controllers;

import com.spring.reto.dto.LoginDTO;
import com.spring.reto.dto.UserDTO;
import com.spring.reto.responses.LoginMessage;
import com.spring.reto.entity.User;
import com.spring.reto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public User saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> saveUser(@RequestBody LoginDTO loginDTO){
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }

}
