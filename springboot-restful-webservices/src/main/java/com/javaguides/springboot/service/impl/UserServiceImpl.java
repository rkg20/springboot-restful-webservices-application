package com.javaguides.springboot.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;
import com.javaguides.springboot.mapper.UserMapper;
import com.javaguides.springboot.repository.UserRepository;
import com.javaguides.springboot.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto to User JPA entity
        User user=UserMapper.mapToUser(userDto);

        User savedUser=userRepository.save(user);
        
        // Convert User JPA entity to UserDto

        UserDto savedUserDto=UserMapper.mapToUserDto(savedUser); 
        
         return savedUserDto;
    }

    @Override
    public UserDto getUserById(long userId) {
        User user=userRepository.findById(userId).get();
        UserDto userDto=UserMapper.mapToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User>users=userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        //  Convert UserDto to User JPA
        User user=UserMapper.mapToUser(userDto);

        User existingUser=userRepository.findById(user.getId()).get();

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());

        User updatedUser=userRepository.save(existingUser);

        //  Convert User JPA entity to UserDto

        UserDto updatedUserDto=UserMapper.mapToUserDto(updatedUser);

        return updatedUserDto;
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
    
    
}
