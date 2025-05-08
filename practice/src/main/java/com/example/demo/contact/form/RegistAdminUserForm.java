package com.example.demo.contact.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistAdminUserForm {

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,}$")
    @Size(min = 8, max = 16)
    private String password;

}
