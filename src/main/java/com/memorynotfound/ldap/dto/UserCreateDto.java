package com.memorynotfound.ldap.dto;


import java.util.List;

/**
 * Для создания пользователей.
 */
public class UserCreateDto {

    public Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    public String login;
    public String eMailAddress;

    public String password;

    public Long organizationId;
    public Long roleId;


    public UserCreateDto(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName != null && firstName.isEmpty())
            firstName = null;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName != null && lastName.isEmpty())
            lastName = null;
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if(middleName != null && middleName.isEmpty())
            middleName = null;
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "UserCreateDto{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", login='" + login + '\'' +
                ", eMailAddress='" + eMailAddress + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
