package com.example.demo.admin.service;

import org.springframework.stereotype.Service;

import com.example.demo.admin.entity.AdminUser;
import com.example.demo.admin.form.signInAdminUserForm;
import com.example.demo.contact.form.RegistAdminUserForm;

@Service
public interface AdminUserService {

    public void saveAdminUser(RegistAdminUserForm form);

    public AdminUser findAdminUserByEmail(signInAdminUserForm signInAdminUserForm);

}
