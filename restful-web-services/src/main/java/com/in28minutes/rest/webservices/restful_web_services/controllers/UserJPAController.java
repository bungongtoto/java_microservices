package com.in28minutes.rest.webservices.restful_web_services.controllers;

import com.in28minutes.rest.webservices.restful_web_services.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restful_web_services.models.Post;
import com.in28minutes.rest.webservices.restful_web_services.models.User;
import com.in28minutes.rest.webservices.restful_web_services.repositories.PostRepository;
import com.in28minutes.rest.webservices.restful_web_services.services.UserJPAService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/users")
public class UserJPAController {

    private UserJPAService userJPAService;
    private PostRepository postRepository;

    @Autowired
    public UserJPAController(UserJPAService userJPAService, PostRepository postRepository){
        this.userJPAService = userJPAService;
        this.postRepository = postRepository;
    }

    @GetMapping("")
    public List<User> retrieveAllUsers(){
        return  userJPAService.findAll();
    }


    @GetMapping("/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = userJPAService.findOneById(id);
        if (user == null ) throw  new UserNotFoundException("User Not Found with id: "+ id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("")
    public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user){
        User savedUser = userJPAService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userJPAService.deleteUser(id);
    }

    @GetMapping("/{id}/posts")
    public List<Post> retrieveALlUserPosts(@PathVariable int id){
        User user = userJPAService.findOneById(id);
        if (user == null ) throw  new UserNotFoundException("User Not Found with id: "+ id);

        return  user.getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @Valid @RequestBody Post post){
        User user = userJPAService.findOneById(id);
        if (user == null ) throw  new UserNotFoundException("User Not Found with id: "+ id);
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
