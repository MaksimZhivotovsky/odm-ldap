package com.memorynotfound.ldap.dto;

import lombok.Data;

@Data
public class LdapUserVO {

    private String dn;
    private String fullName;
    private String lastName;
    private String givenName;
    private String mail;
    private String description;
    private String uid;

//    private String dn;
//
//    private String group;
//
//    private String lastName;
//
//    private String firstName;
//
//    private String middleName;
//
//    private String login;
//
    private String password;
//
//    private String keycloakId;
//
//    private String eMailAddress;
}
