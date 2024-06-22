package com.learning.ecommercemanagementsystem.service.impl;

import com.learning.ecommercemanagementsystem.entity.User;
import com.learning.ecommercemanagementsystem.exception.NotFoundException;
import com.learning.ecommercemanagementsystem.repository.UserRepository;
import com.learning.ecommercemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("USER_NOT_FOUND", "User not found with Id: " + userId));
    }

    @Override
    public User create(User user) {
        user.setUuid(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
