package net.andreaskluth.controller;

import net.andreaskluth.models.Person;
import net.andreaskluth.services.PersonService;
import net.andreaskluth.transfers.PersonDto;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @RequestMapping("/add")
  @ResponseBody
  public String add(String firstName, String lastName) {
    if (StringUtils.isEmpty(firstName)) {
      throw new IllegalArgumentException("firstName must not be blank");
    }
    if (StringUtils.isEmpty(lastName)) {
      throw new IllegalArgumentException("lastName must not be blank");
    }

    PersonDto dto = new PersonDto();
    dto.firstName = firstName;
    dto.lastName = lastName;
    personService.save(dto);
    return "OK+";
  }

  @RequestMapping("/")
  @ResponseBody
  public String home() {
    Page<Person> personPage = personService.findAll(new PageRequest(0, 10));
    return "<p>Person count: " + String.valueOf(personService.count()) +
      "</p><p>First Page: <br>" + personPage.getContent() + "</p>";
  }
}
