package com.uas.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;

import com.uas.hotel.repository.VisitorRepository;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class })
@EnableJpaRepositories("com.uas.hotel.repository")
@ComponentScan({
		"com.uas.hotel.repository",
		"com.uas.hotel.service",
		"com.uas.hotel.controller",
		"com.uas.hotel.domain",
})
public class HotelManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);
	}

	@Autowired
	private VisitorRepository visitorRepository;

	public static Authentication authenticatedUser;
	@Override
	public void run(String... args) throws Exception {

		
	}

}
