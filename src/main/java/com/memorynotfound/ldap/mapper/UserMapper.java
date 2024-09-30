package com.memorynotfound.ldap.mapper;

import com.memorynotfound.ldap.model.LdapUser;
import com.memorynotfound.ldap.model.User_DB;
import org.springframework.ldap.support.LdapNameBuilder;

public class UserMapper {

    private UserMapper() {}

//    public static User_DB mapToUserDB(LdapUser ldapUser) {
//        return User_DB.builder()
//                .firstName(ldapUser.getFullName())
//                .lastName(ldapUser.getLastName())
//                .middleName(ldapUser.getGivenName())
//                .password(ldapUser.getPassword())
//                .eMailAddress(ldapUser.getMail())
//                .build();
//    }

//    public static LdapUser mapToLdapUser(User_DB userDb) {
//
//        String name = ((userDb.getLastName()==null) ? "" : (userDb.getLastName())) + " "
//                + ((userDb.getFirstName()== null) ? "" : (userDb.getFirstName()))
//                + " " + ((userDb.getMiddleName() == null) ? "" :(userDb.getMiddleName())).trim();
//
//        return LdapUser.builder()
//                .dn(LdapNameBuilder.newInstance("cn=" + name + ",ou=user-db").build())
//                .fullName(name)
//                .lastName(userDb.getLastName())
//                .givenName(userDb.getFirstName())
//                .uid(userDb.getKeycloakId())
//                .mail(userDb.getEMailAddress())
//                .password(userDb.getPassword())
//                .build();
//    }
}
