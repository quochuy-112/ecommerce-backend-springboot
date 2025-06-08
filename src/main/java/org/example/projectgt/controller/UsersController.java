package org.example.projectgt.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.UserUpdateRequest;
import org.example.projectgt.dto.request.UsersCreationRequest;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.service.UsersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    UsersService usersService;

    @PostMapping("")
    UsersResponse createUser(@RequestBody UsersCreationRequest request) {
        return usersService.createRequest(request);
    }

    @GetMapping("")
    List<UsersResponse> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{gmail}")
    UsersResponse getUser(@PathVariable String gmail) {
       return usersService.getUserByEmail(gmail);
    }

    @PutMapping("/{email}")
    UsersResponse updateUser(@PathVariable String email,@RequestBody UserUpdateRequest request){
        return usersService.updateUser(email,request);
    }

    @DeleteMapping("/{email}")
    void deleteUser(@PathVariable String email) {
        usersService.deleteUser(email);
    }
}
