package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    Set<String> roles;
    Set<OrdersResponse> orders;
}
