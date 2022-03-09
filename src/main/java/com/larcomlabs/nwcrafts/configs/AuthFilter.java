package com.larcomlabs.nwcrafts.configs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larcomlabs.nwcrafts.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthFilter
{
    private AuthenticationManager manager;
    @Autowired
    private ObjectMapper mapper;

    public AuthFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException
    {
        Map<String, String> map = mapper.readValue(request.getReader(), new TypeReference<Map<String, String>>(){});
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
        return manager.authenticate(authToken);
    }

    public Map<String, String> successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException
    {
        User u = (User) authResult.getPrincipal();
        Algorithm algo = Algorithm.HMAC256("cloudhauth0");
        String accessToken = JWT.create()
                .withSubject(u.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*360*24*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algo);
        String refreshToken = JWT.create()
                .withSubject(u.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10*360*24*1000)) //10 days
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algo);
       Map<String, String> tokens = new HashMap<>();
       tokens.put("access_token", accessToken);
       tokens.put("refresh_token", refreshToken);
        response.addCookie(new Cookie("access_token",accessToken));
       return tokens;
    }
}
