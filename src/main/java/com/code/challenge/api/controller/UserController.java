package com.code.challenge.api.controller;

import com.code.challenge.api.UserApi;
import com.code.challenge.api.request.CreateUserInput;
import com.code.challenge.codegen.types.UpdateUserInput;
import com.code.challenge.model.User;
import com.code.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    public User createUser(CreateUserInput input) {
        return userService.createUser(input);
    }

    public User updateUser(UUID id, UpdateUserInput input) {
        return userService.updateUser(id, input);
    }

    public User deleteUser(UUID id) {
        return userService.deleteUser(id);
    }

    public User user(UUID id) {
        return userService.userById(id);
    }

    public List<User> users() {
        return userService.findAll();
    }

    public Page<User> usersPage(int page, int size) {
        return userService.findAll(PageRequest.of(page, size));
    }
}
