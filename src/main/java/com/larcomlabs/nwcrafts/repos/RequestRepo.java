package com.larcomlabs.nwcrafts.repos;

import com.larcomlabs.nwcrafts.models.CraftRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepo extends JpaRepository<CraftRequest, Integer>
{
}
