package com.mm.jalc.catalog.api;

import com.mm.jalc.catalog.models.Phone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Api(value = "Catalog of Phones", tags = { "Phones" })
public interface PhoneApi {

    @ApiOperation(value = "get Catalog Phoes")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", defaultValue = "0",
                paramType = "query", value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "int", defaultValue = "10",
                paramType = "query", value = "Number of records per page."),
    })
    Page<Phone> getPhones(Pageable pageable);

    ResponseEntity<Phone> createPhone(@RequestBody Phone phone);

    ResponseEntity<Boolean> validateListPhones(@RequestParam List<UUID> ids);
}
