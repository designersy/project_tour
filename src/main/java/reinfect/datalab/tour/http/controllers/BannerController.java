package reinfect.datalab.tour.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "_pages/management/banner/update";
    }

}
