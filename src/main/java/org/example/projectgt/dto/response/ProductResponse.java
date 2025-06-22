package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    String id;
    String name;
    String description;
    BigDecimal price;

    Set<ProductHardwareResponse> productHardwares;

    Set<ProductImgResponse> productImgs;
}
