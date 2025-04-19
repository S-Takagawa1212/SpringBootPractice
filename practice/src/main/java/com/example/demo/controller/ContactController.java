package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ContactForm;

@Controller
public class ContactController {
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("contactForm",new ContactForm());
		
		return "contact";
	}
	
	
	@PostMapping("/contact")
	public String contact(@Validated @ModelAttribute ContactForm contactForm, BindingResult result, HttpServletRequest request 
		                     	) {
		if (result.hasErrors()) {
			return "contact";
		}
		
		// sessionにcontactaFormオブジェクトをcontactFormというタグで登録。
		HttpSession session =request.getSession();
		session.setAttribute("contactForm", contactForm);
		
		
		return "redirect:/contact/confirm";
	}
	
		@GetMapping("/contact/confirm")
		public String confirm(Model model, HttpServletRequest request) {
			
			HttpSession session = request.getSession();
			
			ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
			
		    // nullチェックを追加
		    if (contactForm == null) {
		        // セッションが切れたか、データがない場合はフォーム画面に戻す
		        return "redirect:/contact";
		    }
			
			model.addAttribute("contactForm", contactForm);
			// session.removeAttribute("contactForm");
			
			return "confirmation";
		}
		
}
