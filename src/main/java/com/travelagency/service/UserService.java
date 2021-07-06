package com.travelagency.service;

import com.travelagency.model.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserService {

    private final UserRepo repo;

    /*************************************************************************
     * Check if userId already exist
     *
     * @return Boolean
     *************************************************************************/
    public Boolean existsByUserId(String userId) {
        return repo.existsByUserIdIgnoreCase(userId);
    }

    /*************************************************************************
     * Check if email already exist
     *
     * @return Boolean
     *************************************************************************/
    public Boolean existsByEmail(String email) {
        return repo.existsByEmailIgnoreCase(email);
    }
}
