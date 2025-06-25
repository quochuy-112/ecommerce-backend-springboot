package org.example.projectgt.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.validation.OnCreate;
import org.example.projectgt.validation.OnUpdate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGroupCreation {
    @NotNull(message = "CATEGORYGROUP_NAME_INVALID", groups = {OnCreate.class, OnUpdate.class})
    String name;

    @NotNull(message = "CATEGORY_ID_INVALID", groups = {OnCreate.class})
    String categoryId;
}
