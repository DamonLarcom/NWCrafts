package com.larcomlabs.nwcrafts.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CrafterUser
{
    private String username;
    private String password;
    private String server;
    private List<String> tradeSkills;
    private List<CraftRequest> completedRequests;
    private List<CraftRequest> pendingRequests;
    private int tipsEarned;

    public CrafterUser(String username, String password, List<String> tradeSkills, int tipsEarned, String server) {
        this.username = username;
        this.password = password;
        this.tradeSkills = tradeSkills;
        this.server = server;
        this.tipsEarned = 0;
        this.completedRequests = new ArrayList<>();
        this.pendingRequests = new ArrayList<>();
    }

}
