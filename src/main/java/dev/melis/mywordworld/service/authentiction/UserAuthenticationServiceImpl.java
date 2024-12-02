package dev.melis.mywordworld.service.authentiction;

import dev.melis.mywordworld.config.JwtService;
import dev.melis.mywordworld.repository.UserRepository;
import dev.melis.mywordworld.support.passwordencoder.PasswordEncoderAdaptor;
import dev.melis.mywordworld.support.result.CreationResult;
import dev.melis.mywordworld.support.result.OperationFailureReason;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoderAdaptor passwordEncoderAdaptor;
    private final JwtService jwtService;
    private static Long userId;

    public UserAuthenticationServiceImpl(UserRepository userRepository, PasswordEncoderAdaptor passwordEncoderAdaptor, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoderAdaptor = passwordEncoderAdaptor;
        this.jwtService = jwtService;
    }

    @Override
    public CreationResult authenticate(AuthenticationServiceRequest authenticationRequest) {
        var user = userRepository.findByEmail(authenticationRequest.getEmail());
        if(user.isEmpty()) {
            return CreationResult.failure(OperationFailureReason.NOT_FOUND,"User not found");
        }
        if(!passwordEncoderAdaptor.matches(authenticationRequest.getPassword(), user.get().getPassword())){
            return CreationResult.failure(OperationFailureReason.UNAUTHORIZED,"Incorrect password");
        }
        var jwtToken = jwtService.generateToken(user.get());
        userId = user.get().getId();
        return CreationResult.success(jwtToken);
    }
}
