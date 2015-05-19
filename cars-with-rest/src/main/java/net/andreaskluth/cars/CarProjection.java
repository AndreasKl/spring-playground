package net.andreaskluth.cars;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "noDoors", types = { Car.class }) 
interface CarProjection {

	@Value("#{target.make} #{target.model}")
	String getAuto();
	
}
