package com.mm.jalc.orders.api;

import com.mm.jalc.orders.dto.OrderCreate;
import com.mm.jalc.orders.models.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

@Api(value = "Orders", tags = { "Orders" })
public interface OrderApi {

    ResponseEntity<Order> create(@RequestBody OrderCreate order);

    ResponseEntity<Order> getById(@ApiParam UUID id);

    Page<Order> getOrders(@ApiIgnore Pageable pageable);

}
