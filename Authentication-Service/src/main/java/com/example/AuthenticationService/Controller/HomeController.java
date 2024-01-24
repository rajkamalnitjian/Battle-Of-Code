package com.example.AuthenticationService.Controller;

import com.example.AuthenticationService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.AuthenticationService.Model.User;
import java.util.*;
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public List<User> GetUsers()
    {
        return userService.getUsers();
    }
}
