package dev.melis.mywordworld.service.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserServiceRequest {
    private String name;
    private String surname;
    @Email @NotBlank
    private String email;
    @Size(min = 6, max = 20)
    private String password;

    public UserServiceRequest setName(String name) {
        this.name = name;
        return this;
    }
    public UserServiceRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }
    public UserServiceRequest setEmail(String email) {
        this.email = email;
        return this;
    }
    public UserServiceRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
