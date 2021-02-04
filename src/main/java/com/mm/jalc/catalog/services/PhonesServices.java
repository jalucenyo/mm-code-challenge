package com.mm.jalc.catalog.services;

import com.mm.jalc.catalog.models.Phone;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface PhonesServices {

    Page<Phone> getPhones(Pageable pageable);

    Phone createPhone(com.mm.jalc.catalog.models.Phone phone);

    Boolean validateListPhones(List<UUID> ids);
}
