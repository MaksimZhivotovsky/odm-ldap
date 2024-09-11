package com.memorynotfound.ldap.model;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"inetOrgPerson", "organizationalPerson", "top"})
public class Person {

    @Id
    private Name dn;
    @Attribute(name = "cn")
    @DnAttribute(value = "cn")
    private String fullname;
    @Attribute(name = "sn")
    private String lastname;
    @Attribute(name = "givenname")
    private String givenname;
    @Attribute(name = "mail")
    private String mail;
    @Attribute(name = "description")
    private String description;
    @Attribute(name = "uid")
    private String uid;
}
