package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    String id;
    String name;

    Set<CategoryGroupResponse> categoryGroups;
}
