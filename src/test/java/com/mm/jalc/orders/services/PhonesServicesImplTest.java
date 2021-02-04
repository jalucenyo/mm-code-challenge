package com.mm.jalc.orders.services;

import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.catalog.repositories.PhonesRepository;
import com.mm.jalc.catalog.services.PhonesServicesImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PhonesServicesImplTest {

    @Mock
    private PhonesRepository phonesRepository;

    private PhonesServicesImpl phonesServices;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        phonesServices = new PhonesServicesImpl(phonesRepository);
    }

    @Test
    public void validatePhoneIdsNotExistInCatalog(){

        when(phonesRepository.findAllById(any())).thenReturn(Collections.EMPTY_LIST);

        Boolean result = phonesServices.validateListPhones(Arrays.asList(UUID.randomUUID(),UUID.randomUUID()));

        Assert.assertFalse(result);
    }


    @Test
    public void validatePhoneIdsExistInCatalog(){

        List<UUID> phonesIds = Arrays.asList(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID());

        List<Phone> phonesMock = Arrays.asList(
                Phone.builder().id(phonesIds.get(0)).name("Phone1").price(BigDecimal.valueOf(10)).build(),
                Phone.builder().id(phonesIds.get(1)).name("Phone2").price(BigDecimal.valueOf(20.99)).build(),
                Phone.builder().id(phonesIds.get(2)).name("Phone3").price(BigDecimal.valueOf(30.50)).build()
        );

        when(phonesRepository.findAllById(any())).thenReturn(phonesMock);

        Boolean result = phonesServices.validateListPhones(phonesIds);

        Assert.assertTrue(result);
    }

}
