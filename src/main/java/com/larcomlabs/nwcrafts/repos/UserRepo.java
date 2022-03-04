package com.larcomlabs.nwcrafts.repos;

import com.larcomlabs.nwcrafts.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String>
{
}
