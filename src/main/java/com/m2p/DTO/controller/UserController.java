package com.m2p.DTO.controller;

import com.m2p.DTO.dto.UserDto;
import com.m2p.DTO.exception.ErrorDetails;
import com.m2p.DTO.exception.ResourceNotFoundException;
import com.m2p.DTO.mapper.UserMapper;
import com.m2p.DTO.model.User;
import com.m2p.DTO.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST API's for User Management",
        description = "CRUD REST APIs - getUserById , updateUserById , deleteUserById , createUser, getAllUsers"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    // build create User REST API
    // http://localhost:8081/api/users/createUser

    @Operation(
            summary = "Create User REST API",
            description = "createUser API is used to save a User details in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }




    // build get user by id REST API
    // http://localhost:8081/api/users/1
    @Operation(
            summary = "Get User By ID REST API",
            description = "getUserById API is used to get a particular User details by ID from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }




    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @Operation(
            summary = "Get All User's REST API",
            description = "getAllUsers API is used to get all User's in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDto = userService.getAllUsers();
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }



    // Build Update User REST API
    @Operation(
            summary = "Update User By ID REST API",
            description = "updateUser API is used to update the User details by ID in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    // http://localhost:8081/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @Valid @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }



    @Operation(
            summary = "Delete User By ID REST API",
            description = "deleteUser API is used to delete a User details by ID from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
    //Handle the Specific Exception and Return a Custom Message

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }
}
