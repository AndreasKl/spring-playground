package net.andreaskluth;

import net.andreaskluth.cars.Car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	Validator beforeCreateCarValidator() {
		return new Validator() {

			@Override
			public void validate(Object target, Errors errors) {
				Car car = (Car) target;
				if (car.getMake() == null) {
					errors.rejectValue("make", "make must be set");
				}
			}

			@Override
			public boolean supports(Class<?> clazz) {
				return Car.class.isAssignableFrom(clazz);
			}
		};

	}

}