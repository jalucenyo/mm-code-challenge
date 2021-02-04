package com.mm.jalc.orders.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhonesAdapterServiceImpl implements PhonesAdapterService{

    @Override
    public boolean existPhonesInCatalog(List<UUID> phones) {

        String ids = phones.stream().map(UUID::toString).collect(Collectors.joining(","));

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("http://localhost:8080/catalog/phones/validate")
                .queryParam("ids", ids);
        log.info(builder.toUriString());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builder.toUriString(), Boolean.class, phones);

    }

}
