package com.example.service.impl;

import com.example.persistence.entities.UserEntity;
import com.example.persistence.enums.RoleEnum;
import com.example.persistence.repositories.UserRepository;
import com.example.service.UserService;
import com.example.mapper.UserRegisterMapper;
import com.example.persistence.binding.UserRegisterBindingModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRegisterMapper userRegisterMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity register(UserRegisterBindingModel userRegisterBindingModel) {
        UserEntity userEntity = userRegisterMapper.toUserEntity(userRegisterBindingModel);
        userEntity.setUserPassword(passwordEncoder.encode(userEntity.getUserPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByUserEmail(email) != null;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
