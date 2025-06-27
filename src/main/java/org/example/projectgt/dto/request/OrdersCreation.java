package org.example.projectgt.dto.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.projectgt.entity.OrderItem;
import org.example.projectgt.entity.Users;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersCreation {
    String id;
    BigDecimal total;
    LocalDate orderDate;

    Set<OrderItemCreation> orderItems;

    String userId;
}
