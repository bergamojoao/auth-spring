package com.example.auth.features.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull @Email String email, @NotNull String password){}