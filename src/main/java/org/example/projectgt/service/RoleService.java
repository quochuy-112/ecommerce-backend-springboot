package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.RoleCreation;
import org.example.projectgt.dto.response.RoleResponse;
import org.example.projectgt.mapper.RoleMapper;
import org.example.projectgt.repository.PermissionRepository;
import org.example.projectgt.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse createRole(RoleCreation roleCreation){
        var role = roleMapper.toRole(roleCreation);

        var permission = permissionRepository.findAllById(roleCreation.getPermissions());

        role.setPermissions(new HashSet<>(permission));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAllRoles(){
        var roles = roleRepository.findAll();

        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String roleName){
        roleRepository.deleteById(roleName);
    }
}
