package org.spring_project.calendar.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger Log = LoggerFactory.getLogger(UserController.class);

    private final TokenService tokenService;

    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        Log.debug("Token requested for user: {}", authentication.getName());
        String token = tokenService.generateToken(authentication);
        Log.debug("Token granted {}", token);
        return token;
    }
}
