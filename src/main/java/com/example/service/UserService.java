package com.example.service;

import com.example.persistence.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDetails toUserDetails(UserEntity userEntity);

    UserDetails loadUserByUsername(String username);

    List<UserEntity> getAllUsers();

    boolean emailExists(String email);


    UserDetailsService userDetailsService();


}
