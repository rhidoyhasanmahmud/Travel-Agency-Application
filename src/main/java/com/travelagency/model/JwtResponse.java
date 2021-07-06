package com.travelagency.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String userId;
    private String name;
    private LocalDate date;

    public JwtResponse(String accessToken, String userId, User user) {
        this.token = accessToken;
        this.userId = userId;
        this.id = user.getId();
        this.name = user.getName();
        this.date = LocalDate.now();

    }
}
