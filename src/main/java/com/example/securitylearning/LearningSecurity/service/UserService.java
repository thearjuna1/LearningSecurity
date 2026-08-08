package com.example.securitylearning.LearningSecurity.service;

import com.example.securitylearning.LearningSecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String getUserName(Long id ){
        return userRepository.findNameById(id);
    }

    public String userName(Long id){
        return "Arjun";
    }
}
