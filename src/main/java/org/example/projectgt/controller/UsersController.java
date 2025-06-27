package org.example.projectgt.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.UsersCreation;
import org.example.projectgt.dto.response.ApiResponse;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/user")
public class UsersController {
    UsersService usersService;

    @PostMapping("")
    public ApiResponse<UsersResponse> createUsers(@Valid @RequestBody UsersCreation usersCreation) {
        ApiResponse<UsersResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(usersService.createUsers(usersCreation));

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<UsersResponse> getUserById(@PathVariable String id) {
        ApiResponse<UsersResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(usersService.getUserById(id));

        return apiResponse;
    }

    @GetMapping("")
    public ApiResponse<List<UsersResponse>> getAllUsers() {
        ApiResponse<List<UsersResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setData(usersService.getAllUsers());

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUsers(@PathVariable String id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        usersService.deleteUsers(id);
        apiResponse.setData("User deleted successfully");
        return apiResponse;
    }
}
