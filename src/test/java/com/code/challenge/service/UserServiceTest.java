package com.code.challenge.service;

import com.code.challenge.api.request.CreateUserInput;
import com.code.challenge.codegen.types.UpdateUserInput;
import com.code.challenge.model.User;
import com.code.challenge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        CreateUserInput input = new CreateUserInput("Lucas Barros", "lucas@example.com");
        User expectedUser = new User(UUID.randomUUID(), "Lucas Barros", "lucas@example.com");

        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User createdUser = userService.createUser(input);

        assertEquals(expectedUser, createdUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        UUID userId = UUID.randomUUID();
        UpdateUserInput input = new UpdateUserInput("Pedro Wagner", "pedro-w@example.com");
        User existingUser = new User(userId, "Lucas Barros", "lucas@example.com");
        User updatedUser = new User(userId, "Pedro Wagner", "pedro-w@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(userId, input);

        assertEquals(updatedUser, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        User existingUser = new User(userId, "Lucas Barros", "lucas@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(existingUser);
    }

    @Test
    void testUserById() {
        UUID userId = UUID.randomUUID();
        User expectedUser = new User(userId, "Lucas Barros", "lucas@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        User result = userService.userById(userId);

        assertEquals(expectedUser, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(UUID.randomUUID(), "Lucas Barros", "lucas@example.com"));
        userList.add(new User(UUID.randomUUID(), "Pedro Wagner", "pedro-w@example.com"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        assertEquals(userList, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindAllWithPageable() {
        Pageable pageable = Pageable.unpaged();
        List<User> userList = new ArrayList<>();
        userList.add(new User(UUID.randomUUID(), "Lucas Barros", "lucas@example.com"));
        userList.add(new User(UUID.randomUUID(), "Pedro Wagner", "pedro-w@example.com"));
        Page<User> expectedPage = new PageImpl<>(userList);

        when(userRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<User> result = userService.findAll(pageable);

        assertEquals(expectedPage, result);
        verify(userRepository, times(1)).findAll(pageable);
    }
}
