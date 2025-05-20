package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserServiceImpl;

@Controller
@RequestMapping()
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String userTable() {
        return "users";
    }

    @GetMapping("/user")
    public String userOneUser() {
        return "user";
    }


}

