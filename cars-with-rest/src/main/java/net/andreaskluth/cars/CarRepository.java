package net.andreaskluth.cars;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = CarProjection.class)
interface CarRepository extends PagingAndSortingRepository<Car, UUID> {

	@RestResource(path = "makes", rel = "makes")
	Page<Car> findByMake(@Param("make") String make, Pageable pageable);

}
