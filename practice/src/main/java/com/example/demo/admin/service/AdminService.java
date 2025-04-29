package com.example.demo.admin.service;

import java.util.List;

import com.example.demo.admin.data.ContactDataDetailForAdmin;
import com.example.demo.admin.data.ContactDataForAdmin;
import com.example.demo.contact.form.ContactForm;

public interface AdminService {

    List<ContactDataForAdmin> getAllContact();

    ContactDataDetailForAdmin getContactById(Long id);

    void updateContact(Long id, ContactForm form);

    void deleteContact(Long id);
}
