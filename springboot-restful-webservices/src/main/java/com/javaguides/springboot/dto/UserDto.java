package com.javaguides.springboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    private Long id;

    // User first name shouldn't be empty
    @NotEmpty(message = "User first name shouldn't be empty")
    private String firstName;

    // User last name shouldn't be empty
    @NotEmpty(message = "User last name shouldn't be empty")
    private String lastName;

    // User email shouldn't be empty
    // Email should be valid
    @NotEmpty(message = "User Email shouldn't be empty")
    @Email(message = "Email Should be valid")
    private String email;


}
