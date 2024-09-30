package com.memorynotfound.ldap.repo;

import com.memorynotfound.ldap.model.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;

public interface LdapUserRepositoryJpa extends LdapRepository<LdapUser> {

//    LdapUser findByMailAndPassword(String mail, String password);
//    LdapUser findByMail(String mail);
}
