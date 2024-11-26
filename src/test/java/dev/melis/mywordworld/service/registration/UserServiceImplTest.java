package dev.melis.mywordworld.service.registration;

import java.util.Optional;

import dev.melis.mywordworld.model.User;
import dev.melis.mywordworld.repository.UserRepository;

import dev.melis.mywordworld.support.result.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceRequest user;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        user = new UserServiceRequest();
    }

    @Test
    void registerNewUserTest(){

        user.setName("test");
        user.setSurname("test");
        user.setEmail("test@test.com");
        user.setPassword("test");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        assertEquals(OperationResult.SUCCESS, userService.register(user).getResult());
    }

    @Test
    void existUserTest(){

        user.setName("test");
        user.setSurname("test");
        user.setEmail("test@test.com");
        user.setPassword("test");

        var existuser = new User();
        existuser.setName(user.getName());
        existuser.setSurname(user.getSurname());
        existuser.setEmail(user.getEmail());
        existuser.setPassword(user.getPassword());

        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(existuser));
        assertEquals(OperationResult.FAILURE, userService.register(user).getResult());
    }
}