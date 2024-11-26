package dev.melis.mywordworld.controller.authentication;

import dev.melis.mywordworld.service.authentiction.AuthenticationServiceRequest;

public record AuthenticationControllerRequest(
        String email,
        String password
) {
    AuthenticationServiceRequest toServiceRequest() {
        return new AuthenticationServiceRequest()
                .setEmail(email)
                .setPassword(password);
    }
}
