package com.memorynotfound.ldap.service;

import com.memorynotfound.ldap.dto.UserCreateDto;
import com.memorynotfound.ldap.exception.AppExceptions;
import com.memorynotfound.ldap.mapper.UserMapToLdap;
import com.memorynotfound.ldap.model.LdapUser;
import com.memorynotfound.ldap.model.User_DB;
import com.memorynotfound.ldap.repo.LdapUserRepository;
import com.memorynotfound.ldap.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserManagerServiceImpl {

    private final UserRepository repositoryUser;
    private final LdapUserRepository ldapUserRepository;


    public String createUsersForLdap() {

        List<LdapUser> ldapUserList = ldapUserRepository.findAll();

        List<User_DB> userDbList = new ArrayList<>();

        for (LdapUser ldapUser : ldapUserList) {
            User_DB userDb = UserMapToLdap.mapToUserDB(ldapUser);
            userDbList.add(userDb);
        }

        repositoryUser.saveAll(userDbList);

        return "success";
    }

//    @Transactional
//    public User_DB createUser(UserCreateDto valueFront) throws ServiceException {
//        log.info("UserManagerServiceImpl createUser valueFront {}", valueFront);
//        if(valueFront == null) {
//            log.error("Нет данных для создания пользователя.");
//        }
//        if(valueFront.roleId == null) {
//            log.error("Не выбрана роль для пользователя.");
//        }
//
//
//        if (valueFront.getFirstName() == null
//                && valueFront.getLastName() == null
//                && valueFront.getMiddleName() == null){
//            log.error("Не указаны ФИО пользователя.");
//        }
//
//        if(valueFront.login == null || valueFront.login.isEmpty()){
//            log.error("Не указан логин пользователя.");
//        }
//        if(valueFront.password == null || valueFront.password.isEmpty()){
//            log.error("Не указан пароль пользователя.");
//        }
//
//        User_DB user = User_DB.builder()
//                .firstName(valueFront.getFirstName())
//                .lastName(valueFront.getFirstName())
//                .middleName(valueFront.getMiddleName())
//                .login(valueFront.login)
//                .isArchive(false)
//                .password(valueFront.password)
//                .eMailAddress(valueFront.eMailAddress)
//                .organization(valueFront.organizationId)
//                .build();
//
//        user.setKeycloakId(UUID.randomUUID().toString());
//        User_DB userCreated = null;
//        try {
//            ldapUserRepository.createLdapUser(user);
//            userCreated = repositoryUser.save(user);
//        } catch (Exception e) {
//            log.error("UserManagerServiceImpl createUser создание пользователя в Ldap user {}", user);
//        }
//
//        log.info("UserManagerServiceImpl createUser user {}", user);
//        if (userCreated != null) {
//            return userCreated;
//        } else {
//            log.error("UserManagerServiceImpl createUser пользователь не создан");
//            throw new AppExceptions("Пользователь не создан");
//        }
//    }

    /**
     * Изменение пользователя.
     */
    @Transactional
    public User_DB updateUser(UserCreateDto valueFront) throws ServiceException {
        if(valueFront == null) {
            log.error("Нет данных для редактирования пользователя.");
        }
        Optional<User_DB> userOld = repositoryUser.findById(valueFront.id);
        if(!userOld.isPresent()) {
            log.error("Выбранный пользователь - " + valueFront.id + " не найдена в БД.");
        }
        if(userOld.get().getIsArchive()) {
            log.error("Выбранный пользователь - " + valueFront.id + " в архиве.");
        }

        if(valueFront.roleId == null) {
            log.error("Не выбрана роль для пользователя.");
        }

        if (valueFront.getFirstName() == null
                && valueFront.getLastName() == null
                && valueFront.getMiddleName() == null) {
            log.error("Не указаны ФИО пользователя.");

        }

        if (!userOld.get().getLogin().equals(valueFront.login)) {
            log.error("Нельзя изменить логин пользователя.");
        }

        // Изменим пользователя
        User_DB user = User_DB.builder()
                .id(userOld.get().getId())
                .firstName(valueFront.getFirstName())
                .lastName(valueFront.getLastName())
                .middleName(valueFront.getMiddleName())
                .login(valueFront.login)
                .isArchive(false)
                .password(valueFront.password)
                .eMailAddress(valueFront.eMailAddress)
                .organization(valueFront.organizationId)
                .keycloakId(userOld.get().getKeycloakId())
                .build();


        User_DB userUpdate = null;

        try {
            ldapUserRepository.update(user);
            userUpdate = repositoryUser.save(user);
        } catch (Exception e) {
            log.error("UserManagerServiceImpl updateUser обновление пользователя в Ldap user {}", user);
        }

        log.info("UserManagerServiceImpl updateUser user {}", user);
        if (userUpdate != null) {
            return userUpdate;
        } else {
            log.error("UserManagerServiceImpl updateUser пользователь не обновлен");
            throw new AppExceptions("Пользователь не обновлен");
        }

    }
}
