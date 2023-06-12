package com.qualle.shapeup.service.mapper;

import com.qualle.shapeup.api.CategoryDto;
import com.qualle.shapeup.api.RegistrationDto;
import com.qualle.shapeup.api.UserDto;
import com.qualle.shapeup.entity.Category;
import com.qualle.shapeup.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category fromDto(CategoryDto dto);

    CategoryDto toDto(Category category);
}