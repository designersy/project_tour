package reinfect.datalab.tour.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reinfect.datalab.tour.services.PlaceService;
import reinfect.datalab.tour.utilities.Tour;

@Controller
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService service;
    private final Tour tour;

    @GetMapping("/place")
    public String place(
            @RequestParam(name = "p", defaultValue = "1") int page,
            @RequestParam(name = "t", defaultValue = "") String searchType,
            @RequestParam(name = "s", defaultValue = "") String searchWord,
            @RequestParam(name = "a", defaultValue = "") String sort,
            Model model
    ) {
        model.addAllAttributes(service.paginatedItems(page, 20, searchType, searchWord, sort));
        return "_pages/public/place/list";
    }

    @GetMapping("/management/place")
    public String managementPlace() {
        try {
            tour.getSeoulApiData(1, 2);
        } catch(Exception exception) {

        }
        return "_pages/management/place/list";
    }

}
