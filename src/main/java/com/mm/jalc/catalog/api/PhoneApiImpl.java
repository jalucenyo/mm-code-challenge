package com.mm.jalc.catalog.api;

import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.catalog.services.PhonesServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/catalog/phones")
public class PhoneApiImpl implements PhoneApi{

    final private PhonesServices phonesServices;

    public PhoneApiImpl(PhonesServices phonesServices) {
        this.phonesServices = phonesServices;
    }

    @Override
    @GetMapping
    public Page<Phone> getPhones( @ApiIgnore Pageable pageable) {
        return phonesServices.getPhones(pageable);
    }

    @Override
    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone){
        return ResponseEntity.ok(phonesServices.createPhone(phone));
    }

    @Override
    @GetMapping(path="/validate")
    public ResponseEntity<Boolean> validateListPhones(@RequestParam List<UUID> ids){
        return ResponseEntity.ok(phonesServices.validateListPhones(ids));
    }

}
