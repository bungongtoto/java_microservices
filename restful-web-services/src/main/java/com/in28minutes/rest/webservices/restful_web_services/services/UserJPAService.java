package com.in28minutes.rest.webservices.restful_web_services.services;

import com.in28minutes.rest.webservices.restful_web_services.models.User;
import com.in28minutes.rest.webservices.restful_web_services.repositories.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserJPAService  {
    private UserRepository userRepository;

    @Autowired
    public UserJPAService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return  userRepository.findAll();
    }

    public User findOneById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public  User save( User user){
       return userRepository.save(user);
    }
}
