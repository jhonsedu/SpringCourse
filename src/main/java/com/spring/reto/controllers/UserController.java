package com.spring.reto.controllers;

import com.spring.reto.dto.UserDTO;
import com.spring.reto.entity.User;
import com.spring.reto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
