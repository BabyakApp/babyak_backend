package Babyak.babyak_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class BabyakBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabyakBackendApplication.class, args);
	}

}
