package com.larcomlabs.nwcrafts.controllers;

import com.larcomlabs.nwcrafts.models.CraftRequest;
import com.larcomlabs.nwcrafts.models.TradeSkill;
import com.larcomlabs.nwcrafts.models.User;
import com.larcomlabs.nwcrafts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
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

    @GetMapping("/{id}/reqFrom")
    public List<CraftRequest> getRequestsFrom(@PathVariable String id) {
        List<CraftRequest> requests = new ArrayList<>();
        User u = repo.findById(id).get();
        for(CraftRequest req: u.getRequestsFrom()) {
            requests.add(req);
        }
        return requests;
    }

    @GetMapping("/{id}/reqTo")
    public List<CraftRequest> getRequestsTo(@PathVariable String id) {
        List<CraftRequest> requests = new ArrayList<>();
        User u = repo.findById(id).get();
        for(CraftRequest req: u.getRequestsTo()) {
            requests.add(req);
        }
        return requests;
    }

    @GetMapping("/crafters")
    public List<User> getCrafters() {
        return repo.findByIsCrafterIsTrue();
    }
}
