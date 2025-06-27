package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {
    String id;
    BigDecimal currentPrice;
    int sale;
    int quantity;
    BigDecimal totalPrice;
}
