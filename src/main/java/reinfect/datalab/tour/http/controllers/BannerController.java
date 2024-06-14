package reinfect.datalab.tour.http.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reinfect.datalab.tour.http.forms.BannerForm;
import reinfect.datalab.tour.services.BannerService;

@Controller
@RequiredArgsConstructor
public class BannerController {

    private final BannerService service;

    @GetMapping("/management/banner")
    public String managementBanner(
        @RequestParam(name = "p", defaultValue = "1") int page,
        Model model
    ) {
        model.addAllAttributes(service.paginatedItems(page, 8));
        return "_pages/management/banner/list";
    }

    @GetMapping("/management/banner/add")
    public String managementBannerAdd(Model model) {
        model.addAttribute("banner", new BannerForm());
        return "_pages/management/banner/add";
    }

    @PostMapping("/management/banner")
    public String managementBannerAddProcess(
        @ModelAttribute(name = "banner") @Valid BannerForm banner,
        @RequestPart(name = "image") MultipartFile image,
        BindingResult bindingResult,
        Model model
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "_pages/management/banner/add";
            }
            service.register(image, banner);
            return "redirect:/management/banner";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "_pages/management/banner/add";
        }
    }

    @GetMapping("/management/banner/{id}")
    public String managementBannerEdit(
        @PathVariable("id") Long id, Model model
    ) {
        try {
            model.addAttribute("banner", service.currentItem(id));
            model.addAttribute("id", id);
            return "_pages/management/banner/update";
        } catch (Exception exception) {
            return "redirect:/management/banner";
        }
    }

    @PostMapping("/management/banner/{id}")
    public String managementBannerAddProcess(
            @ModelAttribute(name = "banner") @Valid BannerForm banner,
            @RequestPart(name = "image") MultipartFile image,
            @PathVariable("id") Long id,
            BindingResult bindingResult,
            Model model
    ) {
        try {
            if (bindingResult.hasErrors()) {
                return "_pages/management/banner/update";
            }
            service.update(image, banner, id);
            return "redirect:/management/banner/" + id;
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "_pages/management/banner/add";
        }
    }

    @GetMapping("/management/banner/delete/{id}")
    public String managementBannerDeleteProcess(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/management/banner";
    }

}
