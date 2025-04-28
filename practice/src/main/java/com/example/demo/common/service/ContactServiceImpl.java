package com.example.demo.common.service;

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
        // 複数のService間で共通仕様のDTOとしてcontactを用いている
        // entityから取得した内部データを外部に出す時に、内部での保存形式のまま出さないためのラッパー
        // 以下の場合は、Formからの入力をentityの形式に合わせるためのラッパー
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
