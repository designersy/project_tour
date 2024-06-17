package reinfect.datalab.tour.http.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reinfect.datalab.tour.entities.Food;
import reinfect.datalab.tour.http.forms.FoodForm;
import reinfect.datalab.tour.services.FoodService;
import reinfect.datalab.tour.utilities.Tour;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService service;
    private final Tour tour;

    @GetMapping("/food")
    public String food(
        @RequestParam(name = "p", defaultValue = "1") int page,
        @RequestParam(name = "t", defaultValue = "") String searchType,
        @RequestParam(name = "s", defaultValue = "") String searchWord,
        @RequestParam(name = "a", defaultValue = "") String sort,
        Model model
    ) {

        model.addAllAttributes(service.paginatedItems(page, 20, searchType, searchWord, sort));
        return "_pages/public/food/list";
    }

    @GetMapping("/management/food")
    public String managementFood() {
        return "_pages/management/food/list";
    }



    @GetMapping("/food/detail")
    public String tourDetail(@RequestParam(value = "id") long id, Model model) throws Exception {
        Food food = service.currentItem(id);

        model.addAttribute("food", food);

        return "_pages/public/food/detail";
    }

    // localDB에 저장된 정보를 update하는 라우터
    // detail에서 실행하여 detail로 돌아간다.
    @GetMapping("/food/update")
    public String update(Model model, @RequestParam("id") long id) throws Exception {

        Food food = service.currentItem(id);

        model.addAttribute("food", food);
        return "_pages/public/food/update";
    }

    @PostMapping("/food/update")
    public String updateProcess(Model model, @RequestParam("id") long id, HttpServletRequest request) throws Exception {

        FoodForm food = new FoodForm();
        food.setName(request.getParameter("name"));
        food.setLanguage(request.getParameter("language"));
        food.setContentUrl(request.getParameter("postUrl"));
        food.setAddress(request.getParameter("address"));
        food.setNewAddress(request.getParameter("newAddress"));
        food.setTelephone(request.getParameter("telephone"));
        food.setWebsite(request.getParameter("website"));
        food.setBusinessTime(request.getParameter("businessTime"));
        food.setAccess(request.getParameter("access"));
        food.setMainFood(request.getParameter("mainFood"));

        System.out.println(food.getMainFood());
        service.update(food, id);
        return "redirect:/food/detail?id=" + id;
    }

    @GetMapping("/food/delete")
    public String delete(@RequestParam("id") long id) throws Exception {
        service.delete(id);
        return "redirect:/food";
    }

    // 데이터 추가 페이지로 가는 라우터
    @GetMapping("/food/insert")
    public String insertData() {
        return "_pages/public/food/insert";
    }


    // 데이터 추가
    @PostMapping("/food/insert")
    public String insertDataProcess(HttpServletRequest request) {

        FoodForm food = new FoodForm();
        food.setName(request.getParameter("name"));
        food.setLanguage(request.getParameter("language"));
        food.setContentUrl(request.getParameter("postUrl"));
        food.setAddress(request.getParameter("address"));
        food.setNewAddress(request.getParameter("newAddress"));
        food.setTelephone(request.getParameter("telephone"));
        food.setWebsite(request.getParameter("website"));
        food.setBusinessTime(request.getParameter("businessTime"));
        food.setAccess(request.getParameter("access"));
        food.setMainFood(request.getParameter("mainFood"));

        service.register(food);
        return "redirect:/food/list";
    }

    @GetMapping("/food/initialInsert")
    public String initialInsert() throws Exception {
        List<Food> foodList = service.findAll();

        if (foodList.isEmpty()) {
            try {
                for (int i = 0; i < 30; i += 3) {
                    tour.getMatApiData(i, i+3);
                    if (i == 5500){ break; }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {}
        return "redirect:/food";
    }

}
