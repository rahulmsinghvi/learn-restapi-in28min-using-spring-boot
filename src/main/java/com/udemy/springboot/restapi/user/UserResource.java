package com.udemy.springboot.restapi.user;

import com.udemy.springboot.restapi.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAll()
    {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable int id)
    {
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id: " + id);

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@RequestBody @Valid User user)
    {
        User saved = service.addUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void removeOne(@PathVariable int id)
    {
        User user = service.removeOne(id);
        if(user == null)
            throw new UserNotFoundException("id: " + id);
    }
}
