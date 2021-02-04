package com.mm.jalc.catalog.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@Document(collection = "catalog_phones")
public class Phone {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;

}
