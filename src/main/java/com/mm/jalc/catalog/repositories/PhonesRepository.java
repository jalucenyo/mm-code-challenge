package com.mm.jalc.catalog.repositories;

import com.mm.jalc.catalog.models.Phone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PhonesRepository extends MongoRepository<Phone, UUID> {

}
