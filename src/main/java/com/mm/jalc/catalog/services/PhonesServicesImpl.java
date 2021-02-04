package com.mm.jalc.catalog.services;

import com.google.common.collect.Sets;
import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.catalog.repositories.PhonesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PhonesServicesImpl implements PhonesServices{

    final private PhonesRepository phonesRepository;

    public PhonesServicesImpl(PhonesRepository phonesRepository) {
        this.phonesRepository = phonesRepository;
    }

    @Override
    public Page<Phone> getPhones(Pageable pageable) {
        return phonesRepository.findAll(pageable);
    }

    @Override
    public Phone createPhone(Phone phone) {
        phone.setId(UUID.randomUUID());
        return phonesRepository.save(phone);
    }

    @Override
    public Boolean validateListPhones(List<UUID> ids) {
        HashSet uniqueIds = Sets.newHashSet(ids);
        return uniqueIds.size() == StreamSupport
                .stream(phonesRepository.findAllById(uniqueIds).spliterator(), false).count();
    }


}
