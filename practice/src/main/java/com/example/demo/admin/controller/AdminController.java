package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.admin.service.AdminService;

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

        // TODO idをkeyにしてDBから情報を取得して、それをmodelに格納する

        return "admin/contactDetail";
    }

}
