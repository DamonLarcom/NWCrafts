package com.larcomlabs.nwcrafts.controllers;

import com.larcomlabs.nwcrafts.models.TradeSkill;
import com.larcomlabs.nwcrafts.models.User;
import com.larcomlabs.nwcrafts.repos.TradeRepo;
import com.larcomlabs.nwcrafts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade")
@CrossOrigin
public class TradeController
{
    @Autowired
    private TradeRepo repo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<TradeSkill> getTrades() {
        return repo.findAll();
    }

    @PostMapping()
    public void createTradeSkill(@RequestBody TradeSkill skill) {
        SecurityContext con = SecurityContextHolder.getContext();
        Authentication auth = con.getAuthentication();
        User u = userRepo.findById(auth.getName()).get();
        skill.setPlayer(u);
        repo.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteTrade(@PathVariable int id) {
        repo.deleteById(id);
    }
}
