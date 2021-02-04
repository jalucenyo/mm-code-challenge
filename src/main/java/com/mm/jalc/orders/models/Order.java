package com.mm.jalc.orders.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Order {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private List<UUID> phone;
    private BigDecimal total;

}
