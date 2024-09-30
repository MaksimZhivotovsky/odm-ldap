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


        return lcs;
    }

//    @Bean
//    public LdapContextSource contextSource() {
//        LdapContextSource contextSource = new LdapContextSource();
//
//        contextSource.setUrl(env.getRequiredProperty("ldap://10.170.192.9:389"));
//        contextSource.setBase(
//                env.getRequiredProperty("DC=1580lab,DC=ru"));
//        contextSource.setUserDn(
//                env.getRequiredProperty("m_zhivotovskiy@1580lab.ru"));
//        contextSource.setPassword(
//                env.getRequiredProperty("Max123456"));
//
//        return contextSource;
//    }

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
