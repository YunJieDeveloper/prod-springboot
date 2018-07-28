package com.prod.springboot.prod;

import com.prod.springboot.prod.utils.DaoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DaoConfiguration.class})
public class ProdServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProdServerApplication.class, args);
	}
}
