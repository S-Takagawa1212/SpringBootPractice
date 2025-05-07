package com.example.demo.common.service;

import java.util.List;

import com.example.demo.admin.data.ContactDataDetailForAdmin;
import com.example.demo.admin.data.ContactDataForAdmin;
import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.form.UpdateContactForm;

public interface ContactService {

    void saveContact(ContactForm contactForm);

    List<ContactDataForAdmin> getAllContact();

    ContactDataDetailForAdmin getContactById(Long id);

    void updateContact(UpdateContactForm form);

    void deleteContact(Long id);
}
