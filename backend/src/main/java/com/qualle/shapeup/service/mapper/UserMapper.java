package com.qualle.shapeup.service.mapper;

import com.qualle.shapeup.api.RegistrationDto;
import com.qualle.shapeup.api.UserDto;
import com.qualle.shapeup.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDto(RegistrationDto dto);

    User fromDto(UserDto dto);

    UserDto toDto(User user);
}