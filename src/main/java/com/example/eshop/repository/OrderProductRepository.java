package com.example.eshop.repository;

import com.example.eshop.mysql.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    Set<OrderProduct> findOrderProductsByOrderId(Long id);

}
