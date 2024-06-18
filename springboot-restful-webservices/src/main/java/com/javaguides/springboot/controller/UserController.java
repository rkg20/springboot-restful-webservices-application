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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController{
    
    @Autowired
    private UserService userService;

    // build create user REST API

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto savedUser=userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get the user by id REST API
    // http://localhost:9090/api/users/1
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
        UserDto userDto=userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // http://localhost:9090/api/users

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }


    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDto userDto){
        userDto.setId(userId);
        UserDto updatedUser=userService.updateUser(userDto);
        return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
    }

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
