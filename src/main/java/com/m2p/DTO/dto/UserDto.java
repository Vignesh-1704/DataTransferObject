package com.m2p.DTO.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "UserDto Model Information"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;

    //User firstName should not be null or Empty
    @Schema(description = "User FirstName")
    @NotEmpty(message = "firstName should not be null or Empty")
    private String firstName;

    //User lastName should not be null or Empty
    @Schema(description = "User LastName")
    @NotEmpty(message = "lastName should not be null or Empty")
    private String lastName;

    //User email should not be null or Empty
    //User email should be a valid email
    @Schema(description = "User Email")
    @NotEmpty(message = "email should not be null or Empty")
    @Email(message = "email should be a valid email")
    private String email;
}
