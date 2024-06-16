package com.javaguides.springboot.mapper;

import com.javaguides.springboot.dto.UserDto;
import com.javaguides.springboot.entity.User;

public class UserMapper {
    
    public static User mapToUser(UserDto userDto){
        User user=new User(
            userDto.getId(),
            userDto.getFirstName(),
            userDto.getLastName(),
            userDto.getEmail()
        );
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto=new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail()
        );
        return userDto;
    }
}
