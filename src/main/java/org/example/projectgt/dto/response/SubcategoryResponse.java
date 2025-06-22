package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryResponse {
    String id;
    String name;

    Set<ProductSubcategoryResponse> productSubcategories;
}
