package dev.melis.mywordworld.controller.registration;

import dev.melis.mywordworld.service.registration.UserServiceRequest;

public record RegistrationControllerRequest(
        String name,
        String surname,
        String email,
        String password
) {
    UserServiceRequest toServiceRequest(){
        return new UserServiceRequest()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(password);
    }

}
