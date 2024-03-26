package com.code.challenge.service;

import com.code.challenge.api.request.CreateUserInput;
import com.code.challenge.codegen.types.UpdateUserInput;
import com.code.challenge.model.User;
import com.code.challenge.repository.UserRepository;
import com.code.challenge.service.exceptions.CustomGraphQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(final CreateUserInput input) {
        User user = new User(input.name(), input.email());
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
                .orElseThrow(() -> new CustomGraphQLException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
