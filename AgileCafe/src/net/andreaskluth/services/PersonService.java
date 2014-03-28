package net.andreaskluth.services;


import net.andreaskluth.models.Person;
import net.andreaskluth.transfers.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
  void create(Person person);

  Page<Person> findAll(Pageable pageable);

  long count();

  void save(PersonDto dto);
}
