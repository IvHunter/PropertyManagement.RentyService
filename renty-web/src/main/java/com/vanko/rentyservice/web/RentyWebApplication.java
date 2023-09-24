package com.vanko.rentyservice.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.vanko.rentyservice.data")
@SpringBootApplication(scanBasePackages = {
		"com.vanko.rentyservice.commonmodels",
		"com.vanko.rentyservice.data",
		"com.vanko.rentyservice.viewmodels",
		"com.vanko.rentyservice.business",
		"com.vanko.rentyservice.web",
		// any other packages that need scanning
})
public class RentyWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(RentyWebApplication.class, args);
	}
}
