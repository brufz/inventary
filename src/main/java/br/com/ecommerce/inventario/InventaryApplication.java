package br.com.ecommerce.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.ecommerce.inventario")
public class InventaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventaryApplication.class, args);
	}

}
