package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.RoleCreation;
import org.example.projectgt.dto.response.RoleResponse;
import org.example.projectgt.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreation roleCreation);

    RoleResponse toRoleResponse(Role role);
}
