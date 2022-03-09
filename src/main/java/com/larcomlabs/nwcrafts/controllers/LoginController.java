package com.larcomlabs.nwcrafts.controllers;

import com.larcomlabs.nwcrafts.configs.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class LoginController
{
    @Autowired
    private AuthFilter authFilter;

    @PostMapping
    @CrossOrigin
    public Map<String, String> authenticateUser(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Authentication auth = authFilter.attemptAuthentication(req, res);
        if (auth.isAuthenticated()){
            try {
                return authFilter.successfulAuthentication(req, res, auth);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
