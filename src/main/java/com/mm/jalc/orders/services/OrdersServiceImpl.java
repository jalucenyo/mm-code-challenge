package com.mm.jalc.orders.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.jalc.orders.exceptions.EmptyListPhonesException;
import com.mm.jalc.orders.exceptions.NotFoundPhonesInOrderException;
import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.catalog.repositories.PhonesRepository;
import com.mm.jalc.orders.dto.OrderCreate;
import com.mm.jalc.orders.exceptions.OrderNotFoundException;
import com.mm.jalc.orders.models.Order;
import com.mm.jalc.orders.repositories.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    final private PhonesAdapterService phonesAdapterService;
    final private OrdersRepository ordersRepository;
    final private PhonesRepository phonesRepository;
    private ObjectMapper mapper;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
                             PhonesRepository phonesRepository,
                             PhonesAdapterService phonesAdapterService) {
        this.ordersRepository = ordersRepository;
        this.phonesRepository = phonesRepository;
        this.phonesAdapterService = phonesAdapterService;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Page<Order> getOrders(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    @Override
    public Order getOrder(UUID id){
        return this.ordersRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Order create(OrderCreate orderCreate) {

        validateListPhoneNotEmpty(orderCreate);
        validateExistPhonesInCatalog(orderCreate);

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .email(orderCreate.getEmail())
                .name(orderCreate.getName())
                .surname(orderCreate.getName())
                .phone(orderCreate.getPhoneIds())
                .total(calculateTotal(orderCreate.getPhoneIds()))
                .build();

        Order orderSaved = this.ordersRepository.save(order);

        logSavedOrder(orderSaved);

        return orderSaved;
    }

    private void logSavedOrder(Order orderSaved) {
        try {
            log.info(mapper.writeValueAsString(orderSaved));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void validateExistPhonesInCatalog(OrderCreate orderCreate) {
        if (!existPhonesInCatalog(orderCreate.getPhoneIds())) {
            throw new NotFoundPhonesInOrderException();
        }
    }

    private void validateListPhoneNotEmpty(OrderCreate orderCreate) {
        if (orderCreate.getPhoneIds() == null || orderCreate.getPhoneIds().size() < 1) {
            throw new EmptyListPhonesException();
        }
    }

    private boolean existPhonesInCatalog(List<UUID> phones) {

        return phonesAdapterService.existPhonesInCatalog(phones);

    }

    private BigDecimal calculateTotal(List<UUID> phones) {

        // TODO: Pending refactor method call API Rest Phones
        return StreamSupport
                .stream(phonesRepository.findAllById(phones).spliterator(), false)
                .map(Phone::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
