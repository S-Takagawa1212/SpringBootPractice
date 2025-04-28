package com.example.demo.admin.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ContactDataForAdmin {

    private Integer id;
    private String lastName;
    private String firstName;
    private String contactType;
    private LocalDateTime createdAt; //java.sql.Dateを使おうとしたが、古くて非推奨らしい。
    private LocalDateTime updatedAt;

}
