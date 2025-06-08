package org.example.projectgt.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.entity.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopProductUpdateRequest {
    String name;
    List<Product> products;
}
