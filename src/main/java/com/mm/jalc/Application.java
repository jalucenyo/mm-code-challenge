package com.mm.jalc;

import com.mm.jalc.catalog.models.Phone;
import com.mm.jalc.catalog.repositories.PhonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private PhonesRepository phonesRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		phonesRepository.saveAll(Arrays.asList(
			Phone.builder().id(UUID.randomUUID()).name("Phone1").description("Phone1 description").imageUrl("http://phone1.png").price(BigDecimal.valueOf(99.199)).build(),
			Phone.builder().id(UUID.randomUUID()).name("Phone2").description("Phone2 description").imageUrl("http://phone2.png").price(BigDecimal.valueOf(199.19)).build(),
			Phone.builder().id(UUID.randomUUID()).name("Phone3").description("Phone3 description").imageUrl("http://phone3.png").price(BigDecimal.valueOf(29)).build(),
			Phone.builder().id(UUID.randomUUID()).name("Phone4").description("Phone4 description").imageUrl("http://phone4.png").price(BigDecimal.valueOf(69.69)).build(),
			Phone.builder().id(UUID.randomUUID()).name("Phone5").description("Phone5 description").imageUrl("http://phone5.png").price(BigDecimal.valueOf(99.9)).build()
		));
	}
}
