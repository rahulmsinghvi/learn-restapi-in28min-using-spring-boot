package com.udemy.springboot.restapi.user;

import com.udemy.springboot.restapi.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> findOne(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id: " + id);

        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        // HATEOAS

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
    {
        User saved = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void removeOne(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Post> findPost(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id: " + id);

        User usr = user.get();
        return usr.getPost();

    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> addPost(@PathVariable int id, @RequestBody Post post)
    {
        Optional<User> opUser = userRepository.findById(id);
        User user = opUser.get();

        post.setUser(user);
        Post saved = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}