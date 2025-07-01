package org.example.projectgt.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.RoleCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.RoleResponse;
import org.example.projectgt.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/role")
public class RoleController {
    RoleService roleService;

    @PostMapping("")
    ApiResponse<RoleResponse> createRole(@RequestBody RoleCreation roleCreation) {
        return ApiResponse.<RoleResponse>builder()
                .data(roleService.createRole(roleCreation))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .data(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{roleName}")
    ApiResponse<String> deleteRole(@PathVariable String roleName) {
        roleService.deleteRole(roleName);

        return ApiResponse.<String>builder()
                .data("Role deleted successfully")
                .build();
    }
}
