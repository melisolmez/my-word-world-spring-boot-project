package dev.melis.mywordworld.service.registration;

import java.util.Optional;

import dev.melis.mywordworld.model.User;
import dev.melis.mywordworld.repository.UserRepository;
import dev.melis.mywordworld.support.result.CreationResult;
import dev.melis.mywordworld.support.result.OperationFailureReason;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreationResult register(UserServiceRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.getEmail());
        if(existUser.isPresent()) {
            return CreationResult.failure(OperationFailureReason.CONFLICT,"user already exists");
        }

        var user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        userRepository.save(user);

        return CreationResult.success();
    }
}
