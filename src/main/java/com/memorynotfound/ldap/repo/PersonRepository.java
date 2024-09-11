package com.memorynotfound.ldap.repo;

import com.memorynotfound.ldap.dto.PersonVO;
import com.memorynotfound.ldap.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    public String create(PersonVO personVO) {
        Person person = new Person();
        person.setDn(LdapNameBuilder.newInstance(personVO.getDn()).build());
        person.setDescription(personVO.getDescription());
        person.setFullname(personVO.getFullName());
        person.setLastname(personVO.getLastName());
        person.setGivenname(personVO.getGivenName());
        person.setMail(personVO.getMail());
        person.setUid(personVO.getUid());

        ldapTemplate.create(person);

        return "success";
    }

    public String update(PersonVO personVO) {
        Person person = new Person();
        person.setDn(LdapNameBuilder.newInstance(personVO.getDn()).build());
        person.setDescription(personVO.getDescription());
        person.setFullname(personVO.getFullName());
        person.setLastname(personVO.getLastName());
        person.setGivenname(personVO.getGivenName());
        person.setMail(personVO.getMail());
        person.setUid(personVO.getUid());

        ldapTemplate.update(person);

        return "success";
    }

    public String delete(String uid) {

        Person person =
                ldapTemplate.findOne(LdapQueryBuilder.query().where("uid").is(uid), Person.class);

        ldapTemplate.delete(person);

        return "success";
    }

    public List<Person> findAll() {
        return ldapTemplate.findAll(Person.class);
    }

    public Person findByUid(String uid) {
        return ldapTemplate.findOne(LdapQueryBuilder.query().where("uid").is(uid), Person.class);
    }

    public Person findByLastName(String lastname) {
        return ldapTemplate.findOne(LdapQueryBuilder.query().where("sn").is(lastname), Person.class);
    }
}
