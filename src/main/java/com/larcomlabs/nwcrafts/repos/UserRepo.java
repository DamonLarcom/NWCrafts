package com.larcomlabs.nwcrafts.repos;

import com.larcomlabs.nwcrafts.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepo extends JpaRepository<User, String>
{
    List<User> findByIsCrafterIsTrue();
}
