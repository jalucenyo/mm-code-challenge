package com.mm.jalc.orders.services;

import com.mm.jalc.orders.dto.OrderCreate;
import com.mm.jalc.orders.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrdersService {


    Order getOrder(UUID id);

    Order create(OrderCreate order);

    Page<Order> getOrders(Pageable pageable);
}
