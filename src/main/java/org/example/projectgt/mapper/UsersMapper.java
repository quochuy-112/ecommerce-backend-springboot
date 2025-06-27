package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.UsersCreation;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toUsers(UsersCreation usersCreation);

    @Mapping(target = "orders", ignore = true)
    UsersResponse toUsersResponse(Users users);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "id", ignore = true)
    Users updateUsers(@MappingTarget Users users, UsersCreation usersCreation);
}
