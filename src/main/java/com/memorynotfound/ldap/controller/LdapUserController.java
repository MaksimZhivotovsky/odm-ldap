package com.memorynotfound.ldap.controller;

import com.memorynotfound.ldap.model.Group;
import com.memorynotfound.ldap.dto.GroupVO;
import com.memorynotfound.ldap.dto.LdapUserVO;
import com.memorynotfound.ldap.model.LdapUser;
import com.memorynotfound.ldap.repo.GroupRepository;
import com.memorynotfound.ldap.repo.LdapUserRepository;
import com.memorynotfound.ldap.repo.LdapUserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ldap")
@RequiredArgsConstructor
public class LdapUserController {

    private final LdapUserRepository ldapUserRepository;
    private final GroupRepository groupRepository;
    private final LdapUserRepositoryJpa ldapUserRepositoryJpa;

//    @GetMapping(value = "/findName/{mail}")
//    public LdapUser findName1(
//            @PathVariable("mail") String mail
//    ) {
//        LdapUser ldapUser = ldapUserRepositoryJpa.findByMail(mail);
//        return ldapUser;
//    }

    @GetMapping(value = "/findName/{mail}/{password}")
    public LdapUser findName(
            @PathVariable("mail") String mail,
            @PathVariable("password") String password
    ) {
        LdapUser ldapUser = ldapUserRepository.findByPassword(mail, password);
        return ldapUser;
    }

    @PostMapping(value = "/add")
    public String createUser(@RequestBody LdapUserVO ldapUserVO) {
        return ldapUserRepository.create(ldapUserVO);
    }

    @PostMapping(value = "/addG")
    public String createGroup(@RequestBody GroupVO groupVO) {
        return groupRepository.create(groupVO);
    }

//    @PutMapping(value = "/update")
//    public String update(@RequestBody LdapUserVO ldapUserVO) {
//        return ldapUserRepository.updateLdap(ldapUserVO);
//    }

    @DeleteMapping(value = "/delete/{uid}")
    public String delete(@PathVariable("uid") String uid) {
        return ldapUserRepository.delete(uid);
    }

    @GetMapping(value = "/findAll")
    public List<LdapUser> findAll() {
        return ldapUserRepository.findAll();
    }

    @GetMapping(value = "/findAllGroup")
    public List<Group> findAllGroup() {
        return groupRepository.findAll();
    }

    @GetMapping(value = "/findByUid/{uid}")
    public LdapUser findByUid(@PathVariable("uid") String uid) {
        return ldapUserRepository.findByUid(uid);
    }

    @GetMapping(value = "/findByLogin/{login}")
    public LdapUser findByLogin(@PathVariable("login") String login) {
        return ldapUserRepository.findByLogin(login);
    }
//
//    @GetMapping(value = "/findByLastName/{lastName}")
//    public LdapUser findByLastName(@PathVariable("lastName") String lastName) {
//        return ldapUserRepository.findByLastName(lastName);
//    }

//    @GetMapping(value = "/findAll-group")
//    public List<Group> findAllGroup() {
//        return groupRepository.findAll();
//    }
}

