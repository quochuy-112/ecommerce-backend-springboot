package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.entity.ProductImg;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    String id;
    String name;
    String description;
    BigDecimal price;

    Set<ProductImg> imgs = new HashSet<>();
}
