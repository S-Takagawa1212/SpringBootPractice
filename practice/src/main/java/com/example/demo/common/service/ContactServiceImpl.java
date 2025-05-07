package com.example.demo.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.admin.data.ContactDataDetailForAdmin;
import com.example.demo.admin.data.ContactDataForAdmin;
import com.example.demo.admin.form.UpdateContactForm;
import com.example.demo.common.entity.Contact;
import com.example.demo.common.repository.ContactRepository;
import com.example.demo.contact.form.CreateContactForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public void saveContact(CreateContactForm contactForm) {
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

    @Override
    public ContactDataDetailForAdmin getContactById(Long id) {

        // 詳細画面用にidの値からデータを1件取得する。
        Optional<Contact> optionalResult = contactRepository.findById(id);

        // idが不正な場合の例外スロー
        Contact resultBeforeDto = optionalResult.orElseThrow(() -> new IllegalArgumentException("idの値が不正です。"));

        // TODO
        // DTO側に@allArgsConstructorと@NoargsConstructorをつけて、インスタンス化する時の引数にgetXXXを渡して記述を簡略化できる
        ContactDataDetailForAdmin result = new ContactDataDetailForAdmin();

        result.setId(resultBeforeDto.getId());
        result.setFirstName(resultBeforeDto.getFirstName());
        result.setLastName(resultBeforeDto.getLastName());
        result.setEmail(resultBeforeDto.getEmail());
        result.setPhone(resultBeforeDto.getPhone());
        result.setZipCode(resultBeforeDto.getZipCode());
        result.setAddress(resultBeforeDto.getAddress());
        result.setBuildingName(resultBeforeDto.getBuildingName());
        result.setContactType(resultBeforeDto.getContactType());
        result.setBody(resultBeforeDto.getBody());
        result.setCreatedAt(resultBeforeDto.getCreatedAt());
        result.setUpdatedAt(resultBeforeDto.getUpdatedAt());

        return result;
    }

    @Override
    public void updateContact(UpdateContactForm form) {

        // 更新用にidの値からデータを1件取得する。
        Optional<Contact> optionalContact = contactRepository.findById(form.getId());

        // idが不正な場合の例外スロー
        Contact contact = optionalContact.orElseThrow(() -> new IllegalArgumentException("idの値が不正です。"));

        // data ⊇ form のとき、formが持っているプロパティ名と一致する値だけをdataにコピーする。
        BeanUtils.copyProperties(form, contact);

        // repositoryのメソッドはEntityしか受け取れない
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {

        // 削除予定のidの値からデータを1件取得する。存在の確認。
        Optional<Contact> optionalContact = contactRepository.findById(id);

        // idが不正な場合の例外スロー
        optionalContact.orElseThrow(() -> new IllegalArgumentException("idの値が不正です。"));

        contactRepository.deleteById(id);
    }

}
