package org.example.projectgt.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreation {
    String name;
    String description;
    Set<String> permissions;
}
