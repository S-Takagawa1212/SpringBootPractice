package com.example.demo.admin.service;

import java.util.List;

import com.example.demo.admin.data.ContactDataDetailForAdmin;
import com.example.demo.admin.data.ContactDataForAdmin;

public interface AdminService {

    List<ContactDataForAdmin> getAllContact();

    ContactDataDetailForAdmin getContactById(Long id);

}
