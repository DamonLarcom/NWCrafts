package com.larcomlabs.nwcrafts.repos;

import com.larcomlabs.nwcrafts.models.TradeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepo extends JpaRepository<TradeSkill, Integer>
{
}
