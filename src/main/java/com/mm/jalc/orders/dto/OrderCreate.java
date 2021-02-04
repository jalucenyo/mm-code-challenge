package com.mm.jalc.orders.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderCreate {

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Email
    private String email;
    @NotEmpty
    private List<UUID> phoneIds;

}
