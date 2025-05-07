package com.example.demo.contact.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.service.ContactService;
import com.example.demo.contact.form.CreateContactForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContactController {

    // @RequireArgsConstructorでインスタンスの単一性を確保
    private final ContactService contactService;

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new CreateContactForm());

        return "contacts/contact";
    }

    @PostMapping("/contact")
    public String contact(@Validated @ModelAttribute CreateContactForm contactForm, BindingResult result,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            return "contacts/contact";
        }

        // sessionにcontactaFormオブジェクトをcontactFormというタグで登録。
        HttpSession session = request.getSession();
        session.setAttribute("contactForm", contactForm);

        return "redirect:/contact/confirm";
    }

    @GetMapping("/contact/confirm")
    public String confirm(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        CreateContactForm contactForm = (CreateContactForm) session.getAttribute("contactForm");

        // nullチェックを追加
        if (contactForm == null) {
            // セッションが切れたか、データがない場合はフォーム画面に戻す
            return "redirect:/contact";
        }

        model.addAttribute("contactForm", contactForm);
        // FIXME　本当は以下のコードでセッションを閉じたいが、こうするとなぜかエラーがでる。
        // session.removeAttribute("contactForm");

        return "contacts/confirmation";
    }

    @PostMapping("contact/register")
    public String register(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        CreateContactForm contactForm = (CreateContactForm) session.getAttribute("contactForm");
        contactService.saveContact(contactForm);

        return "redirect:/contact/complete";
    }

    @GetMapping("/contact/complete")
    public String complete(Model model, HttpServletRequest request) {

        // request.getSession()はsessionを新規作成するのでfalseを引数に用いる。
        if (request.getSession(false) == null) {
            return "redirect:/contact";
        }

        // modelにcotactFormの内容を保存
        HttpSession session = request.getSession();
        CreateContactForm contactForm = (CreateContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);

        // 現在のsessionを無効化する
        session.invalidate();

        return "contacts/completion";
    }

}
