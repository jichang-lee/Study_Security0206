package org.spring.security02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing //BaseEntity 인식
public class Security02Application {

	public static void main(String[] args) {
		SpringApplication.run(Security02Application.class, args);
	}


}
