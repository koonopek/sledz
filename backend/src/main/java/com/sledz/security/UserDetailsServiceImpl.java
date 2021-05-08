package com.sledz.security;

import java.util.List;

import com.sledz.entities.User;
import com.sledz.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    /**
     * Zwraca użytkownika idenfytfikującego się daną nazwą
     * 
     * @param String name
     * 
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        List<User> users = userRepository.findByName(name);

        if(users.size() != 1) {
            throw new UsernameNotFoundException("User with name ["+name+"] not found." );
        }

        return users.get(0);
    }
    
}
