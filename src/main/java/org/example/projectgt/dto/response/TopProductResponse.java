package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.entity.Product;

import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopProductResponse {
    String id;
    String name;
    Set<Product> products = new HashSet<>();
}
