package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.PermissionCreate;
import org.example.projectgt.dto.response.PermissionResponse;
import org.example.projectgt.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreate permissionCreate);

    PermissionResponse toPermissionResponse(Permission permission);
}
