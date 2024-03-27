package com.code.challenge.api.controller;

import com.code.challenge.CodechallengeApplication;
import com.code.challenge.model.User;
import com.code.challenge.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@ContextConfiguration(classes = CodechallengeApplication.class)
class UserControllerIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("Must save a user successfully")
    void testCreateUser() {

        // language=GraphQL
        String document = """
                    mutation createUser($name: String, $email: String) {
                      createUser(
                        input: {
                            name: $name,
                            email: $email
                        }
                      ) {id name email}
                    }
                """;

        graphQlTester.document(document)
                .variable("name", "Lucas Barros")
                .variable("email", "teste@hotmail.com")
                .execute()
                .path("createUser")
                .entity(User.class)
                .satisfies(user -> {
                    assertNotNull(user.getId());
                    assertEquals("Lucas Barros", user.getName());
                    assertEquals("teste@hotmail.com", user.getEmail());
                });

    }

    @Test
    @Order(2)
    @DisplayName("Must find users successfully")
    void testFindAllUsers() {

        // language=GraphQL
        String document = """
                    query {
                        users {
                         id,
                         name,
                         email
                       }
                     }            
                """;

        graphQlTester.document(document)
                .execute()
                .path("users")
                .entityList(User.class)
                .satisfies(users -> {
                    assertFalse(users.isEmpty());
                    assertTrue(users.stream().anyMatch(user -> user.getName().toLowerCase().contains("lucas")));
                });
    }


}
