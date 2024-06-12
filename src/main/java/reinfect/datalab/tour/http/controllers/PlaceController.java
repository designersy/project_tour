package reinfect.datalab.tour.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reinfect.datalab.tour.services.PlaceService;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService service;

    @GetMapping("/place")
    public String food(
            @RequestParam(name = "p", defaultValue = "1") int page,
            @RequestParam(name = "t", defaultValue = "") String searchType,
            @RequestParam(name = "s", defaultValue = "") String searchWord,
            @RequestParam(name = "a", defaultValue = "") String sort,
            Model model
    ) {
        model.addAllAttributes(service.paginatedItems(page, 20, searchType, searchWord, sort));
        return "_pages/public/place/list";
    }

}
