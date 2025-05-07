package com.example.demo.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.admin.entity.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    AdminUser findByEmail(String email);
}
