package com.larcomlabs.nwcrafts.controllers;

import com.larcomlabs.nwcrafts.models.Alignment;
import com.larcomlabs.nwcrafts.models.CraftRequest;
import com.larcomlabs.nwcrafts.models.User;
import com.larcomlabs.nwcrafts.repos.UserRepo;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{username}")
    public User getById(@PathVariable String username){
        return repo.getById(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        repo.save(u);
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        repo.deleteById(username);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") String username, @RequestBody User u) {
        User old = repo.findById(username).get();
        old.setEmail(u.getEmail());
        old.setServer(u.getServer());
        old.setPassword(encoder.encode(u.getPassword()));
        old.setAlignment(u.getAlignment());
        repo.save(old);
    }


    @PatchMapping("/{id}/req")
    public void addRequest(@PathVariable("id") String username, @RequestBody CraftRequest req) {
       User userRequesting = repo.findById(username).get();
       userRequesting.addRequest(req);
       repo.save(userRequesting);
    }

    @DeleteMapping("/purge")
    public void deleteAll() {
        repo.deleteAll();
    }
}
