package com.learning.ecommercemanagementsystem.service;

import com.learning.ecommercemanagementsystem.entity.User;

import java.util.UUID;

public interface UserService {

    User getById(UUID userId);

    User create(User user);
}