package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.projectgt.dto.request.UsersCreation;
import org.example.projectgt.dto.response.OrdersResponse;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.entity.Users;
import org.example.projectgt.enums.Role;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.OrdersMapper;
import org.example.projectgt.mapper.UsersMapper;
import org.example.projectgt.repository.OrdersRepository;
import org.example.projectgt.repository.UsersRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersService {
    UsersRepository usersRepository;
    OrdersRepository ordersRepository;
    UsersMapper usersMapper;
    OrdersMapper ordersMapper;
    PasswordEncoder passwordEncoder;

    public UsersResponse createUsers(UsersCreation usersCreation) {
        if(usersRepository.existsByEmail(usersCreation.getEmail()))
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);

        Users users = usersMapper.toUsers(usersCreation);

        users.setPassword(passwordEncoder.encode(usersCreation.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        users.setRoles(roles);

        return usersMapper.toUsersResponse(usersRepository.save(users));
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UsersResponse getUserById(String id) {
        if(!usersRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXIST);
        }

        UsersResponse usersResponse = usersMapper.toUsersResponse(usersRepository.findById(id).get());
        Set<OrdersResponse> ordersResponses = ordersRepository.findByUser_Id(id).stream()
                .map(ordersMapper::toOrdersResponse)
                .collect(Collectors.toSet());

        usersResponse.setOrders(ordersResponses);

        return usersResponse;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UsersResponse> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("User name: {}", authentication.getName());
        authentication.getAuthorities().forEach(authority -> log.info("Role: {}", authority.getAuthority()));

        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toUsersResponse)
                .collect(Collectors.toList());
    }

    public void deleteUsers(String id) {
        if(!usersRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXIST);
        }
        usersRepository.deleteById(id);
    }

    public UsersResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Users users = usersRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));

        return usersMapper.toUsersResponse(users);
    }
}
