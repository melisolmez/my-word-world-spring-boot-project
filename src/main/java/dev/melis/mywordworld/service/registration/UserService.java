package dev.melis.mywordworld.service.registration;

import dev.melis.mywordworld.support.result.CreationResult;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    CreationResult register(@Valid UserServiceRequest userRequest);
}
