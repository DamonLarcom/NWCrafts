package com.larcomlabs.nwcrafts.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeSkill
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tradeSkillId;

    @ManyToOne
    @JsonBackReference("trades")
    private User player;

    @Enumerated(EnumType.STRING)
    private TradeType type;
}
