package com.in28minutes.rest.webservices.restful_web_services.services;


import com.in28minutes.rest.webservices.restful_web_services.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDasService is used perform business logic for users in the system
 * It is used directly by any Controller that requires to perform action on a user.
 */
@Service
public class UserService {
    //Later will implement JPA
    //For now we create a static List
    private  static List<User> users = new ArrayList<>();
    private  static  int usersCount = 0;

    public UserService(){
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));

    }

    public List<User> findAll(){
        return new ArrayList<>(users);
    }

    public User findOneById(int id){
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteUser(int id){
        users.removeIf(user -> user.getId().equals(id));
    }

    public  User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return  user;
    }

}
