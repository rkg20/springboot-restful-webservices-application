package com.javaguides.springboot.service.impl;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.exception.EmailAlreadyExistsException;
import com.javaguides.springboot.exception.ResourceNotFoundException;
import com.javaguides.springboot.mapper.UserMapper;
import com.javaguides.springboot.repository.UserRepository;
import com.javaguides.springboot.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto to User JPA entity user userMapper class
        // User user=UserMapper.mapToUser(userDto);

        Optional<User> checkUser =userRepository.findByEmail(userDto.getEmail());

        if(checkUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Present for this User");
        }

        // Convert UserDto to User JPA using model mapper
        User user =modelMapper.map(userDto,User.class);

        User savedUser=userRepository.save(user);
        
        // Convert User JPA entity to UserDto using user mapper class
        // UserDto savedUserDto=UserMapper.mapToUserDto(savedUser); 

        // Convert User JPA entity to UserDto using model mapper
        UserDto savedUserDto=modelMapper.map(savedUser, UserDto.class);
        
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(long userId) {

        User user=userRepository.findById(userId).orElseThrow(
            ()-> new ResourceNotFoundException("User", "Id", userId)
        );
        // Convert the User entity to  UserDto
        // UserDto userDto=UserMapper.mapToUserDto(user);

        UserDto userDto=modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User>users=userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        //  Convert UserDto to User JPA using usermapper class
        // User user=UserMapper.mapToUser(userDto);

        // Optional<User> checkUser =userRepository.findByEmail(userDto.getEmail());

        // if(checkUser.isPresent()){
        //     throw new EmailAlreadyExistsException("Email Already Present for this User");
        // }

        User user=modelMapper.map(userDto, User.class);

        User existingUser=userRepository.findById(user.getId()).orElseThrow(
            ()-> new ResourceNotFoundException("User", "Id", user.getId())
        );

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());

        User updatedUser=userRepository.save(existingUser);

        //  Convert User JPA entity to UserDto using usermapper class
        // UserDto updatedUserDto=UserMapper.mapToUserDto(updatedUser);

        UserDto updatedUserDto=modelMapper.map(updatedUser, UserDto.class);

        return updatedUserDto;
    }

    @Override
    public void deleteUser(long userId) {
        User user=userRepository.findById(userId).orElseThrow(
            ()-> new ResourceNotFoundException("User", "Id", userId)
        );
        userRepository.deleteById(userId);
    }
    
    
}
