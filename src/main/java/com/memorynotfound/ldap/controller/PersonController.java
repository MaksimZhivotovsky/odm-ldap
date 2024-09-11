package com.memorynotfound.ldap.controller;

import com.memorynotfound.ldap.dto.PersonVO;
import com.memorynotfound.ldap.model.Person;
import com.memorynotfound.ldap.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ldap")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(value = "/add")
    public String create(@RequestBody PersonVO personVO) {
        return personRepository.create(personVO);
    }

    @PutMapping(value = "/update")
    public String update(@RequestBody PersonVO personVO) {
        return personRepository.update(personVO);
    }

    @DeleteMapping(value = "/delete/{uid}")
    public String delete(@PathVariable("uid") String uid) {
        return personRepository.delete(uid);
    }

    @GetMapping(value = "/findAll")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/findByUid/{uid}")
    public Person findByUid(@PathVariable("uid") String uid) {
        return personRepository.findByUid(uid);
    }

    @GetMapping(value = "/findByLastName/{lastName}")
    public Person findByLastName(@PathVariable("lastName") String lastName) {
        return personRepository.findByLastName(lastName);
    }

}
