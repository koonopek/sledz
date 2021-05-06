package com.sledz.repositories;

import java.util.List;

import com.sledz.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{ 
    List<User> findByName(String name);
}
