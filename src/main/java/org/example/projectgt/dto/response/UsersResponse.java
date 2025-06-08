package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.entity.Orders;

import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponse {
    String id;
    String email;
    String role;

    Set<Orders> orders = new HashSet<>();
}
