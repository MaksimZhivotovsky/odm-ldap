package com.memorynotfound.ldap.dto;

import lombok.Data;

@Data
public class PersonVO {

    private String dn;
    private String fullName;
    private String lastName;
    private String givenName;
    private String mail;
    private String description;
    private String uid;
    private String password;
}
