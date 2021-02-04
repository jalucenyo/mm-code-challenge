package com.mm.jalc.orders.api;

import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.orders.dto.OrderCreate;
import com.mm.jalc.orders.models.Order;
import com.mm.jalc.orders.services.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orders")
public class OrderApiImpl implements OrderApi {

    final private OrdersService ordersService;

    public OrderApiImpl(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderCreate order){
        return ResponseEntity.ok(this.ordersService.create(order));
    }


    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(this.ordersService.getOrder(id));
    }

    @Override
    @GetMapping
    public Page<Order> getOrders(@ApiIgnore Pageable pageable) {
        return ordersService.getOrders(pageable);
    }

}
