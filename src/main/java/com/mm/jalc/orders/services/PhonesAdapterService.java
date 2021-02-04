package com.mm.jalc.orders.services;


import java.util.List;
import java.util.UUID;

public interface PhonesAdapterService {

    boolean existPhonesInCatalog(List<UUID> phones);
}
