package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.admin.form.RegistAdminUserForm;

@Controller
public class AdminAuthController {

    @GetMapping("/admin/signup")
    public String shpwSignUpPage() {
        return "signup";
    }

    @PostMapping("/admin/signup")
    public String SignUpAdminUser(@ModelAttribute RegistAdminUserForm form) {
        //TODO: process POST request

        return "redirect:/admin/contacts";
    }

}
