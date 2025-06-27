package org.example.projectgt.mapper;

import org.example.projectgt.dto.request.OrdersCreation;
import org.example.projectgt.dto.response.OrdersResponse;
import org.example.projectgt.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    @Mapping(target = "orderItems", ignore = true)
    Orders toOrders(OrdersCreation ordersCreation);

    @Mapping(target = "orderItems", ignore = true)
    OrdersResponse toOrdersResponse(Orders orders);
}
