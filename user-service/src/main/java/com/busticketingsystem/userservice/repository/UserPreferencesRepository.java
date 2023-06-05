package com.busticketingsystem.userservice.repository;

import com.busticketingsystem.userservice.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferencesRepository extends JpaRepository<UserPreference, Long>
{
}
