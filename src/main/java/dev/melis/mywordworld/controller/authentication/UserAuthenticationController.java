package dev.melis.mywordworld.controller.authentication;

import dev.melis.mywordworld.service.authentiction.UserAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthenticationController {
    private final UserAuthenticationService authenticationService;

    public UserAuthenticationController(UserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationControllerRequest request) {
        var result = authenticationService.authenticate(request.toServiceRequest());
        if(!result.isSuccess()) {
            return new ResponseEntity<>("Not authenticate", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("token=\"" + result.getMessage() + "\"", HttpStatus.OK);
    }
}
