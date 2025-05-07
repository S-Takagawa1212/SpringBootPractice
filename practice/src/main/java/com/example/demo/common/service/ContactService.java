package com.example.demo.common.service;

import java.util.List;

import com.example.demo.admin.data.ContactDataForAdmin;
import com.example.demo.contact.form.ContactForm;

public interface ContactService {

    void saveContact(ContactForm contactForm);

    List<ContactDataForAdmin> getAllContact();
}
