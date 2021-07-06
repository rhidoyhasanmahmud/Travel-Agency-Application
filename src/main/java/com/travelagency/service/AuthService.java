package com.travelagency.service;

import com.travelagency.model.JwtResponse;
import com.travelagency.model.User;
import com.travelagency.model.UserRepo;
import com.travelagency.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepo repo;
    private final PasswordEncoder encoder;

    public ResponseEntity<?> signIn(User user) {
        User userInfo = repo.findByUserIdOrEmail(user.getUserId(), user.getEmail());

        if (userInfo == null)
            return new ResponseEntity<>("User not found!", HttpStatus.UNAUTHORIZED);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUserId(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(userInfo);


        return ResponseEntity.ok(new JwtResponse(jwt, userInfo.getUserId(), userInfo));
    }

    public ResponseEntity<?> userRegistration(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return new ResponseEntity<>("User Created Successfully!", HttpStatus.OK);
    }
}
