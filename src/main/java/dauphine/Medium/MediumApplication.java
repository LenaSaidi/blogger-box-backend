package dauphine.Medium;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Medium API",
				version = "1.0",
				description = "API for managing Medium categories and articles",
				contact = @io.swagger.v3.oas.annotations.info.Contact(
						name = "Léna SAIDI",
						email = "lena.saidi@dauphine.eu"
				)
		)
)

public class MediumApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediumApplication.class, args);
	}

}
