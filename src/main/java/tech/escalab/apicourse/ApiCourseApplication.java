package tech.escalab.apicourse;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Courses", version = "1.0", description = "API to work with courses"))
public class ApiCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCourseApplication.class, args);
	}

}
