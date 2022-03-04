package com.larcomlabs.nwcrafts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "craft_requests")
public class CraftRequest
{
    @Id
    private String requestId;
    private Date date;
    private String trade;
    private int tip;

    @ManyToOne
    private User requester;

    public CraftRequest(String trade, int tip){
        this.requestId = UUID.randomUUID().toString();
        this.date = new Date();
        this.trade = trade;
        this.tip = tip;
    }
}
