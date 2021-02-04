package com.mm.jalc.orders.services;

import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.catalog.repositories.PhonesRepository;
import com.mm.jalc.orders.dto.OrderCreate;
import com.mm.jalc.orders.exceptions.EmptyListPhonesException;
import com.mm.jalc.orders.exceptions.NotFoundPhonesInOrderException;
import com.mm.jalc.orders.models.Order;
import com.mm.jalc.orders.repositories.OrdersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrdersServiceImplTest {

    @MockBean
    private PhonesAdapterService phonesAdapterService;
    @MockBean
    private OrdersRepository ordersRepository;
    @MockBean
    private PhonesRepository phonesRepository;

    private OrdersServiceImpl ordersService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        ordersService = new OrdersServiceImpl(ordersRepository, phonesRepository, phonesAdapterService);
    }

    @Test
    public void calculateTotalOrder(){

        List<UUID> phonesIds = Arrays.asList(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID());

        List<Phone> phonesMock = Arrays.asList(
                Phone.builder().id(phonesIds.get(0)).name("Phone1").price(BigDecimal.valueOf(10)).build(),
                Phone.builder().id(phonesIds.get(1)).name("Phone2").price(BigDecimal.valueOf(20.99)).build(),
                Phone.builder().id(phonesIds.get(2)).name("Phone3").price(BigDecimal.valueOf(30.50)).build()
        );

        when(phonesRepository.findAllById(any())).thenReturn(phonesMock);
        when(ordersRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
        when(phonesAdapterService.existPhonesInCatalog(any())).thenReturn(true);

        OrderCreate orderCreate = OrderCreate.builder()
                .email("test@test.local")
                .name("TestName")
                .surname("TestSurName")
                .phoneIds(phonesIds)
                .build();

        Order orderResult  = ordersService.create(orderCreate);

        Assert.assertEquals(BigDecimal.valueOf(61.49), orderResult.getTotal());

    }


    @Test
    public void createOrderWithPhoneNOTExistsInCatalog(){

        List<UUID> phonesIds = Arrays.asList(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID());

        when(phonesAdapterService.existPhonesInCatalog(any())).thenReturn(false);

        OrderCreate orderCreate = OrderCreate.builder()
                .email("test@test.local")
                .name("TestName")
                .surname("TestSurName")
                .phoneIds(phonesIds)
                .build();

        Exception exception = Assert.assertThrows(NotFoundPhonesInOrderException.class, () ->
                ordersService.create(orderCreate));

        Assert.assertTrue(exception.getMessage().contains("Not found phones in catalog."));

    }

    @Test
    public void createOrderListPhonesEmpty(){

        OrderCreate orderCreate = OrderCreate.builder()
                .email("test@test.local")
                .name("TestName")
                .surname("TestSurName")
                .phoneIds(Collections.emptyList())
                .build();

        Exception exception = Assert.assertThrows(EmptyListPhonesException.class,
                () -> ordersService.create(orderCreate));

        Assert.assertTrue(exception.getMessage().contains("List phones empty, in order"));

    }

}
