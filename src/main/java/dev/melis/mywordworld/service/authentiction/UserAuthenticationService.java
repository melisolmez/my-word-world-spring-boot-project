package dev.melis.mywordworld.service.authentiction;

import dev.melis.mywordworld.support.result.CreationResult;

public interface UserAuthenticationService {
    CreationResult authenticate(AuthenticationServiceRequest request);
}
