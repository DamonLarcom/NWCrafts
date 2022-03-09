package com.larcomlabs.nwcrafts.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "craft_requests")
public class CraftRequest
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    private Date date;
    private int tip;

    @Enumerated(EnumType.STRING)
    private TradeType trade;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JsonBackReference
    private User requester;

    @ManyToOne
    @JsonBackReference
    private User requestee;
}
