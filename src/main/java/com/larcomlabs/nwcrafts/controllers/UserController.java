package com.larcomlabs.nwcrafts.controllers;

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

    @PutMapping("/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User u) {
        User user = repo.findById(username).get();
        //only allow changes to the email and server being played on.
        user.setEmail(u.getEmail());
        user.setServer(u.getServer());
    }

    @PutMapping("/{id}/req")
    public void addRequest(@PathVariable("id") String username, @RequestBody String req) {
        User u = repo.getById(username);

        JSONObject obj = new JSONObject(req);
        int tip = obj.getInt("tip");
        String trade = obj.getString("trade");

        CraftRequest request = new CraftRequest(trade, tip);
        u.addRequest(request);
        repo.save(u);
    }
}
