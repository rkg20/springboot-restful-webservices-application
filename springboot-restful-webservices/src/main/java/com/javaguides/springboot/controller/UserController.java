package com.javaguides.springboot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.exception.ErrorDetails;
import com.javaguides.springboot.exception.ResourceNotFoundException;
import com.javaguides.springboot.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(
    name = "CRUD REST APIs for User Resource",
    description = "CRUD REST APIs  - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController{
    
    @Autowired
    private UserService userService;

    // build create user REST API

    @Operation(
        summary = "CREATE User REST APIs",
        description = "Create user REST API is used to save user in a database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Code 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser=userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get the user by id REST API
    // http://localhost:9090/api/users/1

    @Operation(
        summary = "GET User By Id REST APIs",
        description = "GET User By Id REST API is used to fetch single user from a database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Code 200 SUCCESS"
    )
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
        UserDto userDto=userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // http://localhost:9090/api/users

    @Operation(
        summary = "GET User REST APIs",
        description = "GET User REST API is used to fetch All User from a database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Code 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    @Operation(
        summary = "UPDATE User REST APIs",
        description = "UPDATE User REST API is used to update the user details in a database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Code 200 SUCCESS"
    )
    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDto userDto){
        userDto.setId(userId);
        UserDto updatedUser=userService.updateUser(userDto);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
    }

    @Operation(
        summary = "DELETE User REST APIs",
        description = "DELETE User REST API is used to delete particular user from a database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Code 200 SUCCESS"
    )
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User deleted Successfully", HttpStatus.OK);
    }


    // Below Method moved to the GlobalExceptionHandler Class
    /*  
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails=new ErrorDetails(
            LocalDateTime.now(),
            exception.getMessage(),
            webRequest.getDescription(false),
            "USER_NOT_FOUND"

        );
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    */
}
