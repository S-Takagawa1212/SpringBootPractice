package com.example.demo.common.data;

import lombok.Data;

// FormとEntityの間を中継させる
// はずのクラスだが使われていない。消してもいいと思う。
@Data
public class ContactData {
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String zipCode;
    private String address;
    private String buildingName;
    private String contactType;
    private String body;
}
