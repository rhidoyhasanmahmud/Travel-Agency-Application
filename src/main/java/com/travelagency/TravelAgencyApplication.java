package com.travelagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class TravelAgencyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TravelAgencyApplication.class, args);
	}

}
