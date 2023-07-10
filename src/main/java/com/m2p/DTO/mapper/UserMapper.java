package com.m2p.DTO.mapper;

import com.m2p.DTO.dto.UserDto;
import com.m2p.DTO.model.User;

public class UserMapper {

    //Convert UserDto To Entity
    public static UserDto mapToUserDTO(User user)
    {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    //Convert UserDto to Entity
    public static User mapToEntity(UserDto userDto)
    {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
