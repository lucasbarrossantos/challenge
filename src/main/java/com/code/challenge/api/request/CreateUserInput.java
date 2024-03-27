package com.code.challenge.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserInput(
        @NotBlank String name,
        @Email(message = "must be a well-formed email address")
        @NotBlank
        String email) { }
