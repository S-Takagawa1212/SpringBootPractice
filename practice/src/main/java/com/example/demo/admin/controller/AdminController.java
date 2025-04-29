package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.admin.service.AdminService;
import com.example.demo.contact.form.ContactForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/contacts")
    public String showContacts(Model model) {

        model.addAttribute("contactDataForAdmin", adminService.getAllContact());

        return "admin/contactList";
    }

    @GetMapping("admin/contacts/{id}")
    public String showContactsDeteil(@PathVariable("id") Long id, Model model) {

        // idをkeyにしてDBから情報を取得して、それをmodelに格納する
        model.addAttribute("contactDataDetailForAdmin", adminService.getContactById(id));

        return "admin/contactDetail";
    }

    @GetMapping("admin/contacts/{id}/edit")
    public String showContactEdit(@PathVariable("id") Long id, Model model) {

        // idをkeyにしてDBから情報を取得して、それをmodelに格納する
        model.addAttribute("contactDataDetailForAdmin", adminService.getContactById(id));

        return "admin/contactEdit";
    }

    @PostMapping("admin/contacts/{id}/edit")
    public String editContact(@ModelAttribute ContactForm form, @PathVariable Long id) {
        //TODO:DBのデータを上書きする
        System.out.println("データを上書きしたってばよ！");
        // TODO 上書きしました、という表示と変更後のデータが入力されたhtmlを表示させる
        return "redirect:/admin/contacts";
    }

}
