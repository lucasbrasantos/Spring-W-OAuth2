package com.lucas.OAuth2.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Secured";
    }

    @GetMapping("/token")
    public String getToken(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauthUser = oauthToken.getPrincipal();

            if(oauthUser instanceof OidcUser){
                String idToken = ((OidcUser) oauthUser).getIdToken().getTokenValue();
                return "ID Token: " + idToken;
            }


            String accessToken = oauthToken.getAuthorizedClientRegistrationId();
            return "Access Token: " + accessToken;
        }
        return "No OAuth2 token found";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "Logged out successfully";
    }
}
