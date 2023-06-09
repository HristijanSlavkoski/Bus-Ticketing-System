package com.busticketingsystem.userservice.repository;

import com.busticketingsystem.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
