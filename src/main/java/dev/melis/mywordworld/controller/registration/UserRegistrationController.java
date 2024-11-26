package dev.melis.mywordworld.controller.registration;

import dev.melis.mywordworld.service.registration.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationControllerRequest request){
        var result= userService.register(request.toServiceRequest());
        if(!result.isSuccess()){
            return new ResponseEntity<>("User has already registered", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
