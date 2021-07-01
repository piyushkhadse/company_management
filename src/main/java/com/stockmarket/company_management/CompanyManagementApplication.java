package com.stockmarket.company_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableMongoRepositories(basePackages = {"com.stockmarket"})
@ComponentScan(basePackages = {"com.stockmarket"})
@SpringBootApplication
@EnableSwagger2
public class CompanyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementApplication.class, args);
	}

}
