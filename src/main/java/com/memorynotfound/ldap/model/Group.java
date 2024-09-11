package com.memorynotfound.ldap.model;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"top", "organizationalUnit"})
public final class Group {

    @Id
    private Name dn;
    @Attribute(name = "ou")
    private String nameGroup;

}