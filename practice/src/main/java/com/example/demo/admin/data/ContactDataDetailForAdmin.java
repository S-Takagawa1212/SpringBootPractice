package com.example.demo.admin.data;

import java.time.LocalDateTime;

import lombok.Data;

// 今回の実装では、DBのcontactテーブルと内容が全く同じなので、短期的には不要なDTO定義となるが
// DBの設計変更時にコード側に変更影響をもたらさないためのクリーンアーキテクチャの考えを尊重してこのDTOを定義する
@Data
public class ContactDataDetailForAdmin {

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String zipCode;
    private String address;
    private String buildingName;
    private String contactType;
    private String body;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
