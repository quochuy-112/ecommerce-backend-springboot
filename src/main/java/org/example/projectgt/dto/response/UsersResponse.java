package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponse {
    String id;
    String email;
    String firstName;
    String lastName;
    LocalDate birthDate;
    Set<RoleResponse> roles;
    Set<OrdersResponse> orders;
}
