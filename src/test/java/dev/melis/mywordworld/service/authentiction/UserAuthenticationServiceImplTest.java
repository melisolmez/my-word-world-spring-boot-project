package dev.melis.mywordworld.service.authentiction;

import java.util.Optional;

import dev.melis.mywordworld.config.JwtService;
import dev.melis.mywordworld.model.User;
import dev.melis.mywordworld.repository.UserRepository;
import dev.melis.mywordworld.support.passwordencoder.PasswordEncoderAdaptor;
import dev.melis.mywordworld.support.result.CreationResult;
import dev.melis.mywordworld.support.result.OperationFailureReason;
import dev.melis.mywordworld.support.result.OperationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private AuthenticationServiceRequest request;

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private PasswordEncoderAdaptor passwordEncoderAdaptor;

    @InjectMocks
    private UserAuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        request = new AuthenticationServiceRequest();
        jwtService = new JwtService();
    }

    @Test
    void userNotFoundTest(){

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        CreationResult result = authenticationService.authenticate(request);

        assertEquals(OperationFailureReason.NOT_FOUND, result.getReason());
        assertEquals("User not found", result.getMessage());
    }

    @Test
    void userLoginTest(){
/*
        request.setEmail("test@test.com");
        request.setPassword("password");

        var user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("encryptedPassword");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoderAdaptor.matches(Mockito.eq(request.getPassword()),Mockito.eq(user.getPassword()))).thenReturn(true);
        when(Mockito.eq(jwtService.generateToken(user))).thenReturn("mockToken");
        CreationResult result = authenticationService.authenticate(request);
        assertEquals(OperationResult.SUCCESS, result.getResult());
        assertEquals("mockToken", result.getMessage());

 */
    }

}