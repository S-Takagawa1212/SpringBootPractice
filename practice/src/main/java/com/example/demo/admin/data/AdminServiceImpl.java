package com.example.demo.admin.data;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.admin.service.AdminService;
import com.example.demo.common.entity.Contact;
import com.example.demo.common.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ContactRepository repository;

    @Override
    public List<ContactDataForAdmin> getAllContact() {
        // repositoryからデータを全件取得
        List<Contact> contactList = repository.findAll();

        // DTOであるcontactdataForAdminのリストとして結果を返却する
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
