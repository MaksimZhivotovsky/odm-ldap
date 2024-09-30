package com.memorynotfound.ldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.core.env.Environment;


@Configuration
public class SecurityConfig {

    @Autowired
    private Environment env;

    @Bean
    public LdapContextSource ldapContextSource(){
        LdapContextSource lcs = new LdapContextSource();
        lcs.setUrl("ldap://10.170.192.9:389");
        lcs.setBase("CN=Users,DC=1580lab,DC=ru");
        lcs.setUserDn("CN=Максим Животовский,CN=Users,DC=1580lab,DC=ru");
        lcs.setPassword("Max123456");

        return lcs;
    }

//    @Bean
//    public LdapContextSource contextSource() {
//        LdapContextSource contextSource = new LdapContextSource();
//        contextSource.setUrl("ldap://localhost:10389");
//        contextSource.setBase("ou=system");
////        contextSource.setUserDn("uid=admin,ou=system");
////        contextSource.setPassword("12345");
//        return contextSource;
//    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(ldapContextSource());
//        return new LdapTemplate(contextSource());
    }
}
