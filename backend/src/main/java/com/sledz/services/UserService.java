package com.sledz.services;


import com.sledz.dtos.UserRegisterDto;
import com.sledz.entities.User;
import com.sledz.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public Long registerUser(UserRegisterDto userRegister) {
        String hashedPassword = this.bCryptPasswordEncoder.encode(userRegister.password);
        
        User newUser = new User(userRegister.name, hashedPassword);

        return this.userRepository.save(newUser).getId();
    }

    
}
