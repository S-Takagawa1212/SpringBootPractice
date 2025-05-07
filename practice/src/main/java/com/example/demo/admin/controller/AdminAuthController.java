package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.admin.form.RegistAdminUserForm;
import com.example.demo.admin.form.signInAdminUserForm;
import com.example.demo.admin.service.AdminUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminUserService service;

    @GetMapping("/admin/signup")
    public String showSignUp(Model model) {
        model.addAttribute("registAdminUserForm", new RegistAdminUserForm());
        return "admin/signup";
    }

    @PostMapping("/admin/signup")
    public String SignUpAdminUser(@Validated @ModelAttribute RegistAdminUserForm registAdminUserForm,
            BindingResult result,
            Model model) {

        // 　formのバリデーションに引っ掛かったらsignup画面に遷移
        if (result.hasErrors()) {
            return "admin/signup";
        }

        service.saveAdminUser(registAdminUserForm);

        return "redirect:/admin/contacts";
    }

    @GetMapping("/admin/signin")
    public String showSignIn(Model model) {
        // TODO SigninAdminUserFormを実装したらコメントを外す。
        // model.addAttribute("signinAdminUserForm", new SigninAdminUserForm());
        return "admin/signin";
    }

    @PostMapping("/admin/signin")
    public String signInAdminUser(@Validated @ModelAttribute signInAdminUserForm signInAdminUserForm,
            BindingResult result, Model model) {

        // formのバリデーションに引っ掛かったらsignup画面に遷移
        if (result.hasErrors()) {
            return "admin/sinin";
        }

        // TODO 遷移先は後で決定する
        return "redirect:/admin/contcts";
    }

}
