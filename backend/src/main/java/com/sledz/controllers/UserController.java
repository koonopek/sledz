package com.sledz.controllers;

import com.sledz.dtos.UserRegisterDto;
import com.sledz.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
class UserController {
    
    @Autowired UserService userService;

    @PostMapping("/user/register")
    public long register(@RequestBody UserRegisterDto userRegisterDto) {
        return this.userService.registerUser(userRegisterDto);
    }
}