package com.example.demo.contact.service;

import org.springframework.stereotype.Service;

import com.example.demo.common.entity.Contact;
import com.example.demo.common.repository.ContactRepository;
import com.example.demo.contact.form.ContactForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public void saveContact(ContactForm contactForm) {
        // TODO 自動生成されたメソッド・スタブ
        Contact contact = new Contact();
        contact.setLastName(contactForm.getLastName());
        contact.setFirstName(contactForm.getFirstName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhone(contactForm.getPhone());
        contact.setZipCode(contactForm.getZipCode());
        contact.setAddress(contactForm.getAddress());
        contact.setBuildingName(contactForm.getBuildingName());
        contact.setContactType(contactForm.getContactType());
        contact.setBody(contactForm.getBody());

        contactRepository.save(contact);
    }

}
