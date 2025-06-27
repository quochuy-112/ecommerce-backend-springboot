package org.example.projectgt.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCreation {
    String id;
    BigDecimal currentPrice;
    int sale;
    int quantity;
    BigDecimal totalPrice;
}
