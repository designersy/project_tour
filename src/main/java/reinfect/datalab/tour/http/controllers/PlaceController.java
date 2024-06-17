package reinfect.datalab.tour.http.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reinfect.datalab.tour.entities.Place;
import reinfect.datalab.tour.http.forms.PlaceForm;
import reinfect.datalab.tour.services.PlaceService;
import reinfect.datalab.tour.utilities.Tour;

import java.util.List;

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

    @GetMapping("/place/detail")
    public String tourDetail(@RequestParam(value = "id") long id, Model model) throws Exception {
        Place place = service.currentItem(id);

        model.addAttribute("place", place);

        return "_pages/public/place/detail";
    }

    @GetMapping("/place/update")
    public String update(Model model, @RequestParam("id") long id) throws Exception {

        Place place = service.currentItem(id);

        model.addAttribute("place", place);
        return "_pages/public/place/update";
    }

    @PostMapping("/place/update")
    public String updateProcess(Model model, @RequestParam("id") long id, HttpServletRequest request) throws Exception {

        PlaceForm place = new PlaceForm();
        place.setName(request.getParameter("name"));
        place.setLanguage(request.getParameter("language"));
        place.setPostUrl(request.getParameter("postUrl"));
        place.setAddress(request.getParameter("address"));
        place.setNewAddress(request.getParameter("newAddress"));
        place.setTelephone(request.getParameter("telephone"));
        place.setWebsite(request.getParameter("website"));
        place.setBusinessTime(request.getParameter("businessTime"));
        place.setBusinessDay(request.getParameter("businessDay"));
        place.setBreakDate(request.getParameter("breakDate"));
        place.setAccess(request.getParameter("access"));
        place.setTags(request.getParameter("tags"));
        place.setHandicap(request.getParameter("handicap"));

        service.update(place, id);
        return "redirect:/place/detail?id=" + id;
    }

    @GetMapping("/place/delete")
    public String delete(Model model, @RequestParam("id") long id) throws Exception {

        service.delete(id);
        return "redirect:/place";
    }

    // 데이터 추가 페이지로 가는 라우터
    @GetMapping("/tour/insert")
    public String insertData() {
        return "_pages/public/place/insert";
    }


    // 데이터 추가
    @PostMapping("/tour/insert")
    public String insertDataProcess(HttpServletRequest request) {

        PlaceForm place = new PlaceForm();
        place.setName(request.getParameter("name"));
        place.setLanguage(request.getParameter("language"));
        place.setPostUrl(request.getParameter("postUrl"));
        place.setAddress(request.getParameter("address"));
        place.setNewAddress(request.getParameter("newAddress"));
        place.setTelephone(request.getParameter("telephone"));
        place.setWebsite(request.getParameter("website"));
        place.setBusinessTime(request.getParameter("businessTime"));
        place.setBusinessDay(request.getParameter("businessDay"));
        place.setBreakDate(request.getParameter("breakDate"));
        place.setAccess(request.getParameter("access"));
        place.setTags(request.getParameter("tags"));
        place.setHandicap(request.getParameter("handicap"));

        service.register(place);
        return "redirect:/place/list";
    }

    @GetMapping("/management/place")
    public String managementPlace() {
        try {
            tour.getSeoulApiData(1, 2);
        } catch(Exception exception) {

        }
        return "_pages/management/place/list";
    }

    @GetMapping("/place/initialInsert")
    public String initialInsert() throws Exception {
        List<Place> placeList = service.findAll();

        if (placeList.isEmpty()) {
            try {
                for (int i = 0; i < 2000; i += 200) {
                    tour.getSeoulApiData(i, i+200);
                    if (i == 1968){ break; }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {}
        return "redirect:/place";
    }
}
