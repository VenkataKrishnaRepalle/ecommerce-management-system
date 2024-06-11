package com.learning.ecommercemanagementsystem.repository;

import com.learning.ecommercemanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
