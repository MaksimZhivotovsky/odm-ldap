package com.memorynotfound.ldap.repo;

import com.memorynotfound.ldap.model.User_DB;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User_DB, Long> {
}
