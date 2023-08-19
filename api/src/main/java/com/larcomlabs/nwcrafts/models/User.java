package com.larcomlabs.nwcrafts.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User implements UserDetails
{
    @Id
    private String username;
    private String password;
    private String server;
    @Enumerated(EnumType.STRING)
    private Alignment alignment;
    private String email;
    private boolean isCrafter;

    @OneToMany(mappedBy = "requester")
    @JsonManagedReference("from")
    private List<CraftRequest> requestsFrom;
    @OneToMany(mappedBy = "requestee")
    @JsonManagedReference("to")
    private List<CraftRequest> requestsTo;

    //specific to crafters
    @OneToMany(mappedBy = "player")
    @JsonManagedReference("trades")
    private List<TradeSkill> tradeSkills;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> auths = new ArrayList<>();
        if(isCrafter) {
            auths.add((GrantedAuthority) () -> "ROLE_ADMIN");
        } else {
            auths.add((GrantedAuthority) () -> "ROLE_USER");
        }
        return auths;
    }

    @JsonIgnore
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @JsonProperty
    public void setPassword(String p){
        this.password = p;
    }

    public Alignment getAlignment()
    {
        return alignment;
    }

    public void setAlignment(Alignment alignment)
    {
        this.alignment = alignment;
    }

    public boolean getCrafter() {
        return isCrafter;
    }
}
