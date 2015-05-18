package net.andreaskluth.cars;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID> {

}
