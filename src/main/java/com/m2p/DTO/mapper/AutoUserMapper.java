package com.m2p.DTO.mapper;

import com.m2p.DTO.dto.UserDto;
import com.m2p.DTO.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/* MapStruct works only if both Entity and DTO has the same number of Fields
 *  And we can use @Mapping to map two object with different field names
 *  Example:
 *  In Source : Entity -> User -> private String emailAddress
 *  In Destination : DTO -> UserDto -> private String email
 *  We deal this by using :
 *  @Mapping(source = "emailAddress" , destination = "email)
 * */
@Mapper
public interface AutoUserMapper {

    //We have to define the mapping methods here...
    // We can use MAPPER to call these methods...
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    // User JPA Entity to UserDto
    UserDto mapToUserDto(User user);

    // UserDTO to Entity
    User mapToUser(UserDto userDto);

    /*  MapStruct provides the Implementation for this in Runtime  */
}
