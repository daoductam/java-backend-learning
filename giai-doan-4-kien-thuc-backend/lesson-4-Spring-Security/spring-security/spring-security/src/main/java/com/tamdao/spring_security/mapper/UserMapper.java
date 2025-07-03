package com.tamdao.spring_security.mapper;

import com.tamdao.spring_security.dto.UserCreationRequest;
import com.tamdao.spring_security.dto.UserResponse;
import com.tamdao.spring_security.dto.UserUpdateRequest;
import com.tamdao.spring_security.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
