package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.UserUpdateRequest;
import org.example.projectgt.dto.request.UsersCreationRequest;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.entity.Users;
import org.example.projectgt.mapper.UsersMapper;
import org.example.projectgt.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersService {
    UsersRepository usersRepository;
    UsersMapper usersMapper;

    public UsersResponse createRequest(UsersCreationRequest request){
        if(usersRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists");

        Users users = usersMapper.toUsers(request);
        users.setRole("ROLE_USER");

        return usersMapper.toUsersResponse(usersRepository.save(users));
    }

    public List<UsersResponse> getAllUsers(){
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toUsersResponse)
                .toList();
    }

    public UsersResponse getUserByEmail(String email){
        return usersMapper.toUsersResponse(usersRepository
                .findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found")));
    }

    public UsersResponse updateUser(String email, UserUpdateRequest request){
        Users users = usersRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Email not found"));

        usersMapper.updateUsers(users, request);

        return usersMapper.toUsersResponse(usersRepository.save(users));
    }

    @Transactional
    public void deleteUser(String email){
        usersRepository.deleteByEmail(email);
    }
}
