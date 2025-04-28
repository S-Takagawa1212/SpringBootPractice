package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
