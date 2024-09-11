package com.memorynotfound.ldap.controller;

import com.memorynotfound.ldap.model.Group;
import com.memorynotfound.ldap.dto.GroupVO;
import com.memorynotfound.ldap.dto.LdapUserVO;
import com.memorynotfound.ldap.model.LdapUser;
import com.memorynotfound.ldap.repo.GroupRepository;
import com.memorynotfound.ldap.repo.LdapUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ldap")
@RequiredArgsConstructor
public class LdapUserController {

    private final LdapUserRepository ldapUserRepository;
    private final GroupRepository groupRepository;

    @PostMapping(value = "/add")
    public String createUser(@RequestBody LdapUserVO ldapUserVO) {
        return ldapUserRepository.create(ldapUserVO);
    }

    @PostMapping(value = "/addG")
    public String createGroup(@RequestBody GroupVO groupVO) {
        return groupRepository.create(groupVO);
    }

    @PutMapping(value = "/update")
    public String update(@RequestBody LdapUserVO ldapUserVO) {
        return ldapUserRepository.updateLdap(ldapUserVO);
    }

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

