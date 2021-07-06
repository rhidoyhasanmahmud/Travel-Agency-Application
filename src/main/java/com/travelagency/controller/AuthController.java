package com.travelagency.controller;

import com.travelagency.model.User;
import com.travelagency.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*************************************************************************
 * {@link AuthController} Controller
 *
 * @author Hasan Mahmud
 * @since 2021-07-06
 *************************************************************************/
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthService service;

    /*************************************************************************
     * *
     * * User Sign In Api
     * * @param {@link User}
     * * @return {@link JwtResponse}
     *************************************************************************/
    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        return service.signIn(user);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody User user) {
        return service.userRegistration(user);
    }
}
