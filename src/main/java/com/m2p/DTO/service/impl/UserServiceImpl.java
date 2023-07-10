package com.m2p.DTO.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.DTO.dto.UserDto;
import com.m2p.DTO.mapper.UserMapper;
import com.m2p.DTO.model.User;
import com.m2p.DTO.repository.UserRepository;
import com.m2p.DTO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert the dto Received into a Entity
        User user1 = UserMapper.mapToEntity(userDto);
        User savedUser = userRepository.save(user1);

        //We have to convert the Entity into DTO to return it.
        UserDto userDto1 = UserMapper.mapToUserDTO(savedUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }


    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
