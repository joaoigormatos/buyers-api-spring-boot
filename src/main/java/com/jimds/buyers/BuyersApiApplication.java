package com.jimds.buyers;

import com.jimds.buyers.util.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class BuyersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyersApiApplication.class, args);
	}

}
