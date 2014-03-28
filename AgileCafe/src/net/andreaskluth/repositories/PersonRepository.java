package net.andreaskluth.repositories;

import net.andreaskluth.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
  Page<Person> findAll(Pageable pageable);
}
