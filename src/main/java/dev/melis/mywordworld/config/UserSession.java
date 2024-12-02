package dev.melis.mywordworld.config;

import dev.melis.mywordworld.model.User;

public record UserSession (
        Long id,
        String username,
        User user
){
}
