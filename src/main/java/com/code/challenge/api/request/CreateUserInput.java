package com.code.challenge.api.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserInput(@NotBlank String name, @NotBlank String email) { }
