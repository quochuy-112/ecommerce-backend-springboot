package org.example.projectgt.repository;

import org.example.projectgt.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    List<Orders> findByUser_Id(String id);
}
