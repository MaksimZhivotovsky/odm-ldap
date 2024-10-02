package com.memorynotfound.ldap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entry(objectClasses = {"top", "person", "organizationalPerson", "user"}) //top;person;organizationalPerson;user
public final class LdapUser {

    @Id
    private Name dn;
    @Attribute(name = "cn")
    @DnAttribute(value = "cn")
    private String fullName;
    @Attribute(name = "sn")
    private String lastName;
    @Attribute(name = "givenName")
    private String firsName;
    @Attribute(name = "userPrincipalName")
    private String email;
    @Attribute(name = "sAMAccountName")
    private String login;
    @Attribute(name = "description")
    private String description;
    @Attribute(name = "objectGUID")
    private String keycloakId;
//    private byte[] guid;

    @Attribute(name = "nTSecurityDescriptor")
    private String password;


}
