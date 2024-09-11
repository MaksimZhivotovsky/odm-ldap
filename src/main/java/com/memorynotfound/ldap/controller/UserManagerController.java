package com.memorynotfound.ldap.controller;

import com.memorynotfound.ldap.dto.UserCreateDto;
import com.memorynotfound.ldap.model.User_DB;
import com.memorynotfound.ldap.service.UserManagerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api/user-manager")
@RequiredArgsConstructor
public class UserManagerController {

    private final UserManagerServiceImpl service;


    @PostMapping(path = "/user")
    public User_DB createUser(@RequestBody() UserCreateDto valueFront) {
        log.info("Запрос createUser - Post(Создание пользователя).");

        return service.createUser(valueFront);

    }

    @PutMapping(path = "/user")
    public ResponseEntity<?> editUser(@RequestBody() UserCreateDto valueFront) {
        log.info("Запрос editUser - Put(Редактирование пользователя).");

        var value = service.updateUser(valueFront);

        return new ResponseEntity<>(value, HttpStatus.OK);
    }
}
