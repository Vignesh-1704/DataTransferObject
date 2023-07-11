package com.m2p.DTO.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;

    //User firstName should not be null or Empty
    @NotEmpty(message = "firstName should not be null or Empty")
    private String firstName;

    //User lastName should not be null or Empty
    @NotEmpty(message = "lastName should not be null or Empty")
    private String lastName;

    //User email should not be null or Empty
    //User email should be a valid email

    @NotEmpty(message = "email should not be null or Empty")
    @Email(message = "email should be a valid email")
    private String email;
}
