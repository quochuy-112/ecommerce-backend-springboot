package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.UserUpdateRequest;
import org.example.projectgt.dto.request.UsersCreationRequest;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUsers(UsersCreationRequest request);

    UsersResponse toUsersResponse(Users users);

    void updateUsers(@MappingTarget Users users, UserUpdateRequest request);
}
