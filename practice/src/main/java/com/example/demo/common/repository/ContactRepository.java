package com.example.demo.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.common.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}