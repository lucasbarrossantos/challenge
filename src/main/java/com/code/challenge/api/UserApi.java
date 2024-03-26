package com.code.challenge.api;

import com.code.challenge.api.request.CreateUserInput;
import com.code.challenge.codegen.types.UpdateUserInput;
import com.code.challenge.model.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;
import java.util.UUID;

public interface UserApi {

    @MutationMapping
    User createUser(@Argument @Valid CreateUserInput input);

    @MutationMapping
    User updateUser(@Argument UUID id, @Argument UpdateUserInput input);

    @MutationMapping
    User deleteUser(@Argument UUID id);

    @QueryMapping
    User user(@Argument UUID id);

    @QueryMapping
    List<User> users();

    @QueryMapping
    Page<User> usersPage(@Argument int page, @Argument("size") int size);
}
