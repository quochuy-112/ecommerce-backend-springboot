package org.example.projectgt.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.dto.request.UsersCreation;
import org.example.projectgt.dto.response.OrdersResponse;
import org.example.projectgt.dto.response.UsersResponse;
import org.example.projectgt.entity.Orders;
import org.example.projectgt.entity.Users;
import org.example.projectgt.exception.AppException;
import org.example.projectgt.exception.ErrorCode;
import org.example.projectgt.mapper.OrdersMapper;
import org.example.projectgt.mapper.UsersMapper;
import org.example.projectgt.repository.OrdersRepository;
import org.example.projectgt.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersService {
    UsersRepository usersRepository;
    OrdersRepository ordersRepository;
    UsersMapper usersMapper;
    OrdersMapper ordersMapper;


    public UsersResponse createUsers(UsersCreation usersCreation) {
        if(usersRepository.existsByEmail(usersCreation.getEmail()))
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);

        Users users = usersMapper.toUsers(usersCreation);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        users.setPassword(passwordEncoder.encode(usersCreation.getPassword()));

        return usersMapper.toUsersResponse(usersRepository.save(users));
    }

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

    public List<UsersResponse> getAllUsers() {
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
}
