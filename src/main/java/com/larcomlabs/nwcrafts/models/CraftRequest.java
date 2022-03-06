package com.larcomlabs.nwcrafts.models;

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
    private User requester;

    @ManyToOne
    private User requestee;
}
