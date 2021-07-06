package com.travelagency.controller;

import com.travelagency.service.AuthService;
import com.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*************************************************************************
 * {@link UserController} Controller
 *
 * @author Hasan Mahmud
 * @since 2021-07-06
 *************************************************************************/
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserService service;

    /*************************************************************************
     * Check if userId already exist
     *
     * @return
     *************************************************************************/
    @GetMapping("/exists/byUserId")
    public Boolean existsByUserId(@RequestParam String userId) {
        return service.existsByUserId(userId);
    }

    /*************************************************************************
     * Check if email already exist
     *
     * @return
     *************************************************************************/
    @GetMapping("/exists/byEmail")
    public Boolean existsByEmail(@RequestParam String email) {
        return service.existsByEmail(email);
    }
}
