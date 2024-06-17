package reinfect.datalab.tour.http.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reinfect.datalab.tour.http.forms.MemberForgetForm;
import reinfect.datalab.tour.http.forms.MemberPasswordForm;
import reinfect.datalab.tour.http.forms.MemberRegisterForm;
import reinfect.datalab.tour.http.forms.MemberUpdateForm;
import reinfect.datalab.tour.services.MemberService;
import reinfect.datalab.tour.utilities.Common;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final Common common;
    private final MemberService service;

    @GetMapping("/member/login")
    public String login(
        @RequestParam(name = "status", defaultValue = "") String status,
        Model model
    ) {
        if (status.equals("forget")) model.addAttribute("information", common.getMessage("flash.forget"));
        else if (status.equals("error")) model.addAttribute("information", common.getMessage("flash.login"));

        return "_pages/public/member/login";
    }

    @GetMapping("/member/register")
    public String register(Model model) {
        model.addAttribute("member", new MemberRegisterForm());
        return "_pages/public/member/register";
    }

    @PostMapping("/member/register")
    public String registerProcess(
        @ModelAttribute("member") @Valid MemberRegisterForm member,
        BindingResult bindingResult, Model model
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "_pages/public/member/register";
            }
            service.register(member);
            return "redirect:/member/login";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "_pages/public/member/register";
        }
    }

    @GetMapping("/member/forget")
    public String forget(Model model) {
        model.addAttribute("forget", new MemberForgetForm());
        return "_pages/public/member/forget";
    }

    @PostMapping("/member/forget")
    public String forgetProcess(
        @ModelAttribute("forget") MemberForgetForm forget,
        BindingResult bindingResult, Model model
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "_pages/public/member/forget";
            }
            service.forget(forget);
            return "redirect:/member/login?status=forget";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "_pages/public/member/forget";
        }
    }

    @GetMapping("/member/update")
    public String update(Model model) {
        try {
            model.addAttribute("member", service.currentItemAtUsername(common.getLoginUsername()));
            model.addAttribute("password", new MemberPasswordForm());
            return "_pages/public/member/edit";
        } catch (Exception exception) {
            return "redirect:/member/logout";
        }
    }

    @PostMapping("/member/update/information")
    public String updateInformationProcess(
        @ModelAttribute("member") @Valid MemberUpdateForm member,
        BindingResult bindingResult, Model model
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "_pages/public/member/edit";
            }
            service.updateAtUsername(member, common.getLoginUsername());
            return "redirect:/member/update";
        } catch (Exception exception) {
            model.addAttribute("error", common.getMessage("error.processing"));
            return "redirect:/member/update";
        }
    }

}
