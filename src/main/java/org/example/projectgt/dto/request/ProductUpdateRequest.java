package org.example.projectgt.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.entity.ProductHardware;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUpdateRequest {
    String name;
    String description;
    BigDecimal price;

    Set<ProductHardware> productHardwares = new HashSet<>();
}
