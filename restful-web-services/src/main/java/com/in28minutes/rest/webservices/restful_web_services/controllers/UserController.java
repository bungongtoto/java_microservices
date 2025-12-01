package com.in28minutes.rest.webservices.restful_web_services.controllers;


import com.in28minutes.rest.webservices.restful_web_services.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restful_web_services.models.User;
import com.in28minutes.rest.webservices.restful_web_services.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> retrieveAllUsers(){
        return  userService.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = userService.findOneById(id);
        if (user == null ) throw  new UserNotFoundException("id: "+ id);
        return user;
    }

    @PostMapping("")
    public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
       userService.deleteUser(id);
    }
}
