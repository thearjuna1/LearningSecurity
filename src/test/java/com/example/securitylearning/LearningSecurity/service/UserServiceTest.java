package com.example.securitylearning.LearningSecurity.service;

import com.example.securitylearning.LearningSecurity.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTest {

     @Mock
     UserRepository userRepository;
     @InjectMocks
     UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserNameById(){
        when(userRepository.findNameById(1L)).thenReturn("Arjun");
        String name = userRepository.findNameById(1L);
        assertEquals("Arjun",name);
    }
}
