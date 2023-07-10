package com.m2p.DTO.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.DTO.dto.UserDto;
import com.m2p.DTO.exception.EmailAlreadyExistsException;
import com.m2p.DTO.exception.ResourceNotFoundException;
import com.m2p.DTO.mapper.AutoUserMapper;
import com.m2p.DTO.mapper.UserMapper;
import com.m2p.DTO.model.User;
import com.m2p.DTO.repository.UserRepository;
import com.m2p.DTO.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    //Injecting the Model Mapper Bean in Service class
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent())
        {
            throw new EmailAlreadyExistsException("Email Already Exists");
        }
        //Convert the dto Received into a Entity
        // 1. Using UserMapper class
        //User user1 = UserMapper.mapToEntity(userDto);

        // 2. Using Model Mapper Library
        //User user1 = modelMapper.map(userDto,User.class);

        // 3. Using MapStruct
        User user1 = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user1);

        //We have to convert the Entity into DTO to return it.
        //return UserMapper.mapToUserDTO(savedUser);

        //return modelMapper.map(savedUser, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",userId)
        );
        //return UserMapper.mapToUserDTO(user);
        //return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        // return userList.stream().map(UserMapper::mapToUserDTO).
        //       collect(Collectors.toList());

        //return userList.stream().map((user)->modelMapper.map(user, UserDto.class)).
        //        collect(Collectors.toList());

        return userList.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        //return UserMapper.mapToUserDTO(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","id", userId)
        );
        userRepository.deleteById(userId);
    }
}
