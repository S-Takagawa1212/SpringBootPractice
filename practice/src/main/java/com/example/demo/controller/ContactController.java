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

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ContactController {
	
	// @RequireArgsConstructorでインスタンスの単一性を確保
	private final ContactRepository contactRepository;
	
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
			// FIXME　本当は以下のコードでセッションを閉じたいが、こうするとなぜかエラーがでる。
			// session.removeAttribute("contactForm");
			
			return "confirmation";
		}
		
	@GetMapping("contact/register")
	public String register(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
		
        Contact contact = new Contact();
        contact.setLastName(contactForm.getLastName());
        contact.setFirstName(contactForm.getFirstName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhone(contactForm.getPhone());
        contact.setZipCode(contactForm.getZipCode());
        contact.setAddress(contactForm.getAddress());
        contact.setBuildingName(contactForm.getBuildingName());
        contact.setContactType(contactForm.getContactType());
        contact.setBody(contactForm.getBody());

        contactRepository.save(contact);
		
		return "redirect:/contact/complete";
	}
	
	   @GetMapping("/contact/complete")
	    public String complete(Model model, HttpServletRequest request) {
		   	
		    // request.getSession()はsessionを新規作成するのでfalseを引数に用いる。
	        if (request.getSession(false) == null) {
	          return "redirect:/contact";
	        }

	        HttpSession session = request.getSession();
	        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
	        model.addAttribute("contactForm", contactForm);

	        // 現在のsessionを無効化する
	        session.invalidate();

	        return "completion";
	    }
	
	
}
