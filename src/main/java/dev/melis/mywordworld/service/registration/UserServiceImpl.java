package dev.melis.mywordworld.service.registration;

import java.util.Optional;

import dev.melis.mywordworld.model.User;
import dev.melis.mywordworld.repository.UserRepository;
import dev.melis.mywordworld.support.passwordencoder.PasswordEncoderAdaptor;
import dev.melis.mywordworld.support.result.CreationResult;
import dev.melis.mywordworld.support.result.OperationFailureReason;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderAdaptor passwordEncoderAdaptor;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoderAdaptor passwordEncoderAdaptor) {
        this.userRepository = userRepository;
        this.passwordEncoderAdaptor = passwordEncoderAdaptor;
    }

    @Override
    public CreationResult register(UserServiceRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.getEmail());
        if(existUser.isPresent()) {
            return CreationResult.failure(OperationFailureReason.CONFLICT,"user already exists");
        }

        var user = new User();
        user.setEmail(userRequest.getEmail());
        String hashedPassword = passwordEncoderAdaptor.encode(userRequest.getPassword());
        user.setPassword(hashedPassword);
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        userRepository.save(user);

        return CreationResult.success();
    }
}
