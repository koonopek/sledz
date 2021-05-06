package com.sledz.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.Optional;

import com.sledz.entities.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class UserRepositoryTest {
    

    @Autowired private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    void findAllUsers() {
        User user1 = new User("user1","password1");
        User user2 = new User("user2","password1");

        LinkedList<User> newUsers = new LinkedList<>();
        newUsers.add(user1);
        newUsers.add(user2);

        userRepository.save(user1);
        userRepository.save(user2);

        Iterable<User> foundUsers = userRepository.findAll();

        assertThat(foundUsers).isEqualTo(newUsers);
    }


    @Test
    void throwOnDuplicatedUserName() {
        User user1 = new User("user1","password1");
        User user2 = new User("user1","password1");

        userRepository.save(user1);

        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.save(user2);
            // tutaj jest "bug", bez tego nie rzuci wyjątkiem
            userRepository.findAll();
        });
    }

    @Test
    void findUserByName() {
        User user1 = new User("user1","password1");
        User user2 = new User("user2","password1");

        LinkedList<User> newUsers = new LinkedList<>();
        newUsers.add(user1);
        newUsers.add(user2);

        User user1Saved = userRepository.save(user1);
        userRepository.save(user2);

        Optional<User> foundUser = userRepository.findById(user1Saved.getId());

        assertThat(foundUser.get()).isEqualTo(user1);
    }
}
