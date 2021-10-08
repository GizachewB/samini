package com.example.samini.dto;

import com.example.samini.security.UserPrincipal;
import lombok.Getter;

@Getter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserPrincipal user ;

    public JwtAuthenticationResponse(String accessToken, UserPrincipal userDetails) {
        this.accessToken = accessToken;
        this.user = userDetails;
    }

}
