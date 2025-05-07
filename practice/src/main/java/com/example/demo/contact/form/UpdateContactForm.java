package com.example.demo.contact.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateContactForm implements Serializable {

    @NotNull
    private Long id;

    @NotBlank
    @Pattern(regexp = ".*[^　].*", message = "全角空白のみの入力はできません")
    private String lastName;

    @NotBlank
    @Pattern(regexp = ".*[^　].*", message = "全角空白のみの入力はできません")
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 11)
    @Pattern(regexp = "[0-9]{10,11}", message = "ハイフンなしの半角数字で入力してください。")
    private String phone;

    @NotBlank
    @Pattern(regexp = "[0-9]{3}[-]{0,1}[0-9]{4}")
    private String zipCode;

    @NotBlank
    @Pattern(regexp = ".*[^　].*", message = "全角空白のみの入力はできません")
    private String address;

    @NotBlank
    @Pattern(regexp = ".*[^　].*", message = "全角空白のみの入力はできません")
    private String buildingName;

    @NotEmpty
    @Pattern(regexp = ".*[^　].*", message = "全角空白のみの入力はできません")
    private String contactType;

    @NotBlank
    @Pattern(regexp = ".*[^　].*", message = "全角空白のみの入力はできません")
    private String body;
}
