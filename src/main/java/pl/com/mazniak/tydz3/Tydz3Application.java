package pl.com.mazniak.tydz3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Tydz3Application {

	public static void main(String[] args) {
		SpringApplication.run(Tydz3Application.class, args);
	}

}
