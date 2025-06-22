package org.example.projectgt.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreation {
    String id;

    @NotNull(message = "PRODUCT_NAME_INVALID")
    String name;

    @NotNull(message = "PRODUCT_DESCRIPTION_INVALID")
    String description;

    @NotNull(message = "PRODUCT_PRICE_INVALID")
    BigDecimal price;

    Set<ProductHardwareCreation> productHardwares;

    Set<ProductImgCreation> productImgs;
}
