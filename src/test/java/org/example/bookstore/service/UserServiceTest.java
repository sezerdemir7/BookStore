package org.example.bookstore.service;

import org.example.bookstore.entity.User;
import org.example.bookstore.exception.UserNotFoundException;
import org.example.bookstore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

class UserServiceTest {

    private UserRepository userRepository;
    private SepetService sepetService;
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        sepetService = Mockito.mock(SepetService.class);
        userService = new UserService(userRepository, sepetService, passwordEncoder);
    }

    @Test
    void testGetUserById_WhenUserExists() {
        Long userId = 1L;


        User user = new User();
        user.setId(userId);
        user.setName("Test User");


        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));


        User result = userService.getUserById(userId);


        Assertions.assertEquals(user, result);
        Mockito.verify(userRepository).findById(userId);
    }

    @Test
    void testGetUserById_WhenUserNotExists() {

        Long userId = 1L;


        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
        Mockito.verify(userRepository).findById(userId);
    }

}