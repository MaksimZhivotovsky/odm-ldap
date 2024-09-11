package com.memorynotfound.ldap.repo;

import com.memorynotfound.ldap.dto.LdapUserVO;
import com.memorynotfound.ldap.mapper.UserMapper;
import com.memorynotfound.ldap.model.LdapUser;
import com.memorynotfound.ldap.model.User_DB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Slf4j
@Service
@RequiredArgsConstructor
public class LdapUserRepository {

    private final LdapTemplate ldapTemplate;

    public String create(LdapUserVO ldapUserVO) {
        LdapUser ldapUser = new LdapUser();
//        ldapUser.setDn(LdapNameBuilder.newInstance("cn=" + ldapUserVO.getFullName()
//                +",ou="+ldapUserVO.getGroup()).build());

        ldapUser.setDn(LdapNameBuilder.newInstance(ldapUserVO.getDn()).build());

//        ldapUser.setFullName(ldapUserVO.getFullName());
//        ldapUser.setLastName(ldapUserVO.getLastName());
//        ldapUser.setFirstName(ldapUserVO.getFirstName());
//        ldapUser.setMiddleName(ldapUserVO.getMiddleName());
//        ldapUser.setLogin(ldapUserVO.getLogin());
//        ldapUser.setPassword(ldapUserVO.getPassword());
////        ldapUser.setKeycloakId(ldapUserVO.getKeycloakId());
//        ldapUser.setEMailAddress(ldapUserVO.getEMailAddress());

//        ldapUser.setDn(LdapNameBuilder.newInstance(ldapUserVO.getDn()).build());
        ldapUser.setDescription(ldapUserVO.getDescription());
        ldapUser.setFullName(ldapUserVO.getFullName());
        ldapUser.setLastName(ldapUserVO.getLastName());
        ldapUser.setGivenName(ldapUserVO.getGivenName());
        ldapUser.setMail(ldapUserVO.getMail());
        ldapUser.setUid(ldapUserVO.getUid());

        ldapUser.setPassword(ldapUserVO.getPassword());

        ldapTemplate.create(ldapUser);

        return "success";
    }

    public LdapUser createLdapUser(User_DB userDb) {
        log.info("LdapUserRepository createLdapUser userDb {}", userDb);
        LdapUser ldapUser = UserMapper.mapToLdapUser(userDb);

        ldapTemplate.create(ldapUser);

        return ldapUser;
    }

    public LdapUser findByUid(String uid) {
        return ldapTemplate.findOne(query().where("uid").is(uid), LdapUser.class);
    }

    public void update(User_DB userDb) {
        LdapUser ldapUser = findByUid(userDb.getKeycloakId());
//        String name = ((userDb.getLastName()==null) ? "" : (userDb.getLastName())) + " "
//                + ((userDb.getFirstName()== null) ? "" : (userDb.getFirstName()))
//                + " " + ((userDb.getMiddleName() == null) ? "" :(userDb.getMiddleName())).trim();

        ldapUser.setDn(LdapNameBuilder.newInstance("cn=Животовский Максим Алексеевич,ou=user-db").build());
//        ldapUser.setFullName(name);
        if (userDb.getLastName() != null) {
            ldapUser.setLastName(userDb.getLastName());
        }
        if (userDb.getFirstName() != null) {
            ldapUser.setGivenName(userDb.getFirstName());
        }
        if (userDb.getKeycloakId() != null) {
            ldapUser.setUid(userDb.getKeycloakId());
        }
        if (userDb.getEMailAddress() != null) {
            ldapUser.setMail(userDb.getEMailAddress());
        }
//        if (userDb.getPassword() != null) {
//            ldapUser.setPassword(userDb.getPassword());
//        }

        ldapTemplate.update(ldapUser);
    }

    public String updateLdap(LdapUserVO ldapUserVO) {
        LdapUser ldapUser = new LdapUser();
        ldapUser.setDn(LdapNameBuilder.newInstance(ldapUserVO.getDn()).build());
        ldapUser.setDescription(ldapUserVO.getDescription());
        ldapUser.setFullName(ldapUserVO.getFullName());
        ldapUser.setLastName(ldapUserVO.getLastName());
        ldapUser.setGivenName(ldapUserVO.getGivenName());
        ldapUser.setMail(ldapUserVO.getMail());
        ldapUser.setUid(ldapUserVO.getUid());

        ldapTemplate.update(ldapUser);

        return "success";
    }

    public String delete(String uid) {
        LdapUserVO ldapUserVO =
                ldapTemplate.findOne(LdapQueryBuilder.query().where("uid").is(uid), LdapUserVO.class);

        ldapTemplate.delete(ldapUserVO);

        return "success";
    }

    public List<LdapUser> findAll() {
        return ldapTemplate.findAll(LdapUser.class);
    }

    public List<LdapUser> findByLastName(String lastName) {
        return ldapTemplate.find(query().where("sn").is(lastName), LdapUser.class);
    }
}
