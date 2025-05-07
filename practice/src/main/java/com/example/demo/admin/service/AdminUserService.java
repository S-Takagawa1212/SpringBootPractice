package com.example.demo.admin.service;

import org.springframework.stereotype.Service;

import com.example.demo.contact.form.RegistAdminUserForm;

@Service
public interface AdminUserService {

    public void saveAdminUser(RegistAdminUserForm form);

}
