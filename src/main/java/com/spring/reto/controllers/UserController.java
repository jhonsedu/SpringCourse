package com.spring.reto.controllers;

import com.spring.reto.dto.LoginDTO;
import com.spring.reto.dto.UserDTO;
import com.spring.reto.responses.Message;
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
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        Message message = userService.saveUser(userDTO);
        return ResponseEntity.ok(message);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> saveUser(@RequestBody LoginDTO loginDTO){
        Message message = userService.loginUser(loginDTO);
        return ResponseEntity.ok(message);
    }

}
