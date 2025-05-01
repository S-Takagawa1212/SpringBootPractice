package com.example.demo.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.admin.data.ContactDataForAdmin;
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

    @Override
    public List<ContactDataForAdmin> getAllContact() {
        // 一覧画面表示に必要なデータを取得するため、一旦repositoryからデータを全件取得する
        List<Contact> contactList = contactRepository.findAll();

        // 画面表示に必要な項目のDTOであるcontactdataForAdminのリストとして結果を返却する
        List<ContactDataForAdmin> results = new ArrayList<>();

        for (Contact result : contactList) {

            // dtoの空オブジェクトを作成して、要素を詰める
            ContactDataForAdmin dto = new ContactDataForAdmin();

            dto.setId(result.getId());
            dto.setFirstName(result.getFirstName());
            dto.setLastName(result.getLastName());
            dto.setContactType(result.getContactType());
            dto.setCreatedAt(result.getCreatedAt());
            dto.setUpdatedAt(result.getUpdatedAt());

            results.add(dto);
        }

        return results;
    }

}
