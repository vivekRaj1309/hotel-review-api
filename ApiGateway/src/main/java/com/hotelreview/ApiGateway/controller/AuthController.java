package com.hotelreview.ApiGateway.controller;

import com.hotelreview.ApiGateway.model.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient oAuth2AuthorizedClient,
            @AuthenticationPrincipal OidcUser oidcUser,
            Model model){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(oidcUser.getEmail());
        authResponse.setAccessToken(oAuth2AuthorizedClient.getAccessToken().getTokenValue());
        authResponse.setRefreshToken(oAuth2AuthorizedClient.getRefreshToken().getTokenValue());
        authResponse.setExpiresAt(oAuth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond());
        List<String> authorities = oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        authResponse.setAuthorities(authorities);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
