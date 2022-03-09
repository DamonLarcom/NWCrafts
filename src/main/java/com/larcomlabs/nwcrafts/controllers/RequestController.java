package com.larcomlabs.nwcrafts.controllers;

import com.larcomlabs.nwcrafts.models.CraftRequest;
import com.larcomlabs.nwcrafts.models.RequestStatus;
import com.larcomlabs.nwcrafts.models.User;
import com.larcomlabs.nwcrafts.repos.RequestRepo;
import com.larcomlabs.nwcrafts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/craft")
public class RequestController
{
    @Autowired
    private RequestRepo repo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{id}/for")
    public List<CraftRequest> getCraftRequestsFor(@PathVariable String id) {
        return null;
    }

    @PostMapping
    public void createRequest(@RequestParam String crafter, @RequestBody CraftRequest req)
    {
        SecurityContext con = SecurityContextHolder.getContext();
        Authentication auth = con.getAuthentication();
        User requester = userRepo.findById(auth.getName()).get();

        req.setRequestee(userRepo.findById(crafter).get());
        req.setRequester(requester);

        req.setDate(new Date());
        req.setStatus(RequestStatus.PENDING);
        repo.save(req);
    }
}
