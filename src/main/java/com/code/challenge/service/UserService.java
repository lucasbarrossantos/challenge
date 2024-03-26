package com.code.challenge.service;

import com.code.challenge.codegen.types.CreateUserInput;
import com.code.challenge.codegen.types.UpdateUserInput;
import com.code.challenge.model.User;
import com.code.challenge.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(final CreateUserInput input) {
        User user = new User(input.getName(), input.getEmail());
        return userRepository.save(user);
    }

    public User updateUser(final UUID id, final UpdateUserInput input) {
        User user = userById(id);

        user.update(input);
        return userRepository.save(user);
    }

    public User deleteUser(final UUID id) {
        User user = userById(id);

        userRepository.delete(user);
        return userRepository.save(user);
    }

    public User userById(final UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
