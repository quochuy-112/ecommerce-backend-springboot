package org.example.projectgt.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersResponse {
    String id;
    BigDecimal total;
    LocalDate orderDate;

    Set<OrderItemResponse> orderItems;
}
