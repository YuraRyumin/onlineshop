package com.onlineshop.controller;

import com.onlineshop.domain.User;
import com.onlineshop.dto.UserDTO;
import com.onlineshop.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/userslist")
//    public Iterable<UserDTO> listOfUsers(){
//        return userService.getEmptyDTO()
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/createuser")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveuser")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
