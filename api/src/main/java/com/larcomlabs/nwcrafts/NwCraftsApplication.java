package com.larcomlabs.nwcrafts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebSecurity
public class NwCraftsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(NwCraftsApplication.class, args);
    }
}
