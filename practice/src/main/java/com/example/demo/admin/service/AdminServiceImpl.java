package com.example.demo.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.admin.data.ContactDataForAdmin;
import com.example.demo.common.entity.Contact;
import com.example.demo.common.repository.ContactRepository;

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

}
