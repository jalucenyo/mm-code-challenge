package com.mm.jalc.orders.repositories;

import com.mm.jalc.orders.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrdersRepository extends MongoRepository<Order, UUID> {

}
