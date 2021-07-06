package com.travelagency.security.services;

import com.travelagency.model.User;
import com.travelagency.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userIdOrEmail)
            throws UsernameNotFoundException {

        User user = repo.findByUserIdOrEmail(userIdOrEmail, userIdOrEmail);
        if(user == null)
             new UsernameNotFoundException("User Not Found with -> userid or email : " + userIdOrEmail);

        assert user != null;
        return UserPrinciple.build(user);
    }
}
