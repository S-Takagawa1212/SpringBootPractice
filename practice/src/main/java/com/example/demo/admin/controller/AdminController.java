package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.service.ContactService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ContactService contactService;

    @GetMapping("/admin/contacts")
    public String showContacts(Model model) {

        model.addAttribute("contactDataForAdmin", contactService.getAllContact());

        return "admin/contactList";
    }

}
