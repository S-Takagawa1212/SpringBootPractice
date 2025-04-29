package com.example.demo.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.admin.data.ContactDataDetailForAdmin;
import com.example.demo.admin.data.ContactDataForAdmin;
import com.example.demo.common.entity.Contact;
import com.example.demo.common.repository.ContactRepository;
import com.example.demo.contact.form.ContactForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ContactRepository repository;

    @Override
    public List<ContactDataForAdmin> getAllContact() {
        // 一覧画面表示に必要なデータを取得するため、一旦repositoryからデータを全件取得する
        List<Contact> contactList = repository.findAll();

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
        Optional<Contact> optionalResult = repository.findById(id);

        // idが不正な場合の例外スロー
        Contact resultBeforeDto = optionalResult.orElseThrow(() -> new IllegalArgumentException("idの値が不正です。"));

        //TODO DTO側に@allArgsConstructorと@NoargsConstructorをつけて、インスタンス化する時の引数にgetXXXを渡して記述を簡略化できる
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
    public void updateContact(Long id, ContactForm form) {

        // 更新用にidの値からデータを1件取得する。
        Optional<Contact> optionalContact = repository.findById(id);

        // idが不正な場合の例外スロー
        Contact contact = optionalContact.orElseThrow(() -> new IllegalArgumentException("idの値が不正です。"));

        // data ⊇ form のとき、formが持っているプロパティ名と一致する値だけをdataにコピーする。
        BeanUtils.copyProperties(form, contact);

        // repositoryのメソッドはEntityしか受け取れない
        repository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {

        // 削除予定のidの値からデータを1件取得する。存在の確認。
        Optional<Contact> optionalContact = repository.findById(id);

        // idが不正な場合の例外スロー
        optionalContact.orElseThrow(() -> new IllegalArgumentException("idの値が不正です。"));

        repository.deleteById(id);
    }

}
