package com.travelagency.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserIdOrEmail(String userId, String email);
    Boolean existsByUserIdIgnoreCase(String userId);
    Boolean existsByEmailIgnoreCase(String email);
}
