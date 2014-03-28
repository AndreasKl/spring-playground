package net.andreaskluth.services;

import net.andreaskluth.models.Person;
import net.andreaskluth.repositories.PersonRepository;
import net.andreaskluth.transfers.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component("personService")
@Transactional
public class PersonServiceImpl implements PersonService {

  private PersonRepository personRepository;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public void create(Person person) {
    personRepository.saveAndFlush(person);
  }

  @Override
  public Page<Person> findAll(Pageable pageable){
    return personRepository.findAll(pageable);
  }

  @Override
  public long count() {
    return personRepository.count();
  }

  @Override
  public void save(PersonDto dto) {
    personRepository.save(new Person(dto.firstName, dto.lastName));
  }
}
