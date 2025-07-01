package org.example.projectgt.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.PermissionCreate;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.PermissionResponse;
import org.example.projectgt.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permission")
public class PermissionController {
    PermissionService permissionService;

    @PostMapping("")
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionCreate permissionCreate) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.createPermission(permissionCreate))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<PermissionResponse>> getAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAllPermissions())
                .build();
    }

    @DeleteMapping("/{permissonName}")
    ApiResponse<String> deletePermission(@PathVariable String permissonName) {
        permissionService.deletePermission(permissonName);

        return ApiResponse.<String>builder()
                .data("Permission deleted successfully")
                .build();
    }
}
