package com.code.challenge.api.controller;

import com.code.challenge.codegen.types.CreateUserInput;
import com.code.challenge.codegen.types.UpdateUserInput;
import com.code.challenge.model.User;
import com.code.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        return userService.createUser(input);
    }

    @MutationMapping
    public User updateUser(@Argument UUID id, @Argument UpdateUserInput input) {
        return userService.updateUser(id, input);
    }

    @MutationMapping
    public User deleteUser(@Argument UUID id) {
        return userService.deleteUser(id);
    }

    @QueryMapping
    public User user(@Argument UUID id) {
        return userService.userById(id);
    }

    @QueryMapping
    public List<User> users() {
        return userService.findAll();
    }
}
