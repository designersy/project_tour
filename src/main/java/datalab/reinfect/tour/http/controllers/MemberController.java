package datalab.reinfect.tour.http.controllers;

import datalab.reinfect.tour.http.forms.ForgetForm;
import datalab.reinfect.tour.http.forms.MemberRegisterForm;
import datalab.reinfect.tour.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/member/login")
    public String login() {
        return "_pages/member/login";
    }

    @GetMapping("/member/register")
    public String register(Model model) {
        model.addAttribute("member", new MemberRegisterForm());
        return "_pages/member/register";
    }

    @PostMapping("/member/register")
    public String registerProcess(
        @ModelAttribute("member") @Valid MemberRegisterForm form, BindingResult bindingResult, Model model
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "_pages/member/register";
            }
            service.register(form);
            return "redirect:/member/login";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "_pages/member/register";
        }
    }
    
    @GetMapping("/member/forget")
    public String forget(Model model) {
    	model.addAttribute("forget", new ForgetForm());
    	return "_pages/member/forget";
    }
    
    @PostMapping("/member/forget")
    public String forgetProcess(
    	@ModelAttribute("forget") @Valid ForgetForm forget, BindingResult bindingResult, Model model	
    ) {
    	try {
    		if (bindingResult.hasErrors()) {
    			return "_pages/member/forget";
    		}
    		service.forget(forget);
    		return "redirect:/member/login";
    	} catch (Exception exception) {
    		model.addAttribute("error", exception.getMessage());
    		return "_pages/member/forget";
    	}
    }
}