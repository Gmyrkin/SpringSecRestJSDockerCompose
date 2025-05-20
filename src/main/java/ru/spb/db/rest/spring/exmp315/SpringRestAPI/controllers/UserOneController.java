package ru.spb.db.rest.spring.exmp315.SpringRestAPI.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler.UserErrorResponse;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.service.UserServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class UserOneController {

    private final UserServiceImpl userService;

    public UserOneController (UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/userOne")
    public ResponseEntity<?> getUserById(Principal principal) {



        System.out.println("попал в метод getUserById в UserOneController id под номером..."+0);


        User user = userService.findByUsername(principal.getName());
//        if (user == null || user.getPassword().isEmpty()) {
//            UserErrorResponse userErrorResponse = new UserErrorResponse(
//                    "User with this id wasn't found !",
//                    System.currentTimeMillis()
//            );
//
//            return new ResponseEntity<Object>(userErrorResponse, HttpStatus.NOT_FOUND);
//        }
        return new ResponseEntity<Object>(List.of(user), HttpStatus.OK);
    }

    @PostMapping("/user/getTable")
    public ResponseEntity<List<User>> getUser(HttpSession session) {


        System.out.println("попал в метод getUser в UserOneController");


        List<User> userList = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        userList.add(user);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


}
