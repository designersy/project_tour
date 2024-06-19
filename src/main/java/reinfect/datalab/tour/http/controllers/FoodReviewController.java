package reinfect.datalab.tour.http.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import reinfect.datalab.tour.services.FoodReviewService;

@Controller
@RequiredArgsConstructor
public class FoodReviewController {

    private final FoodReviewService reviewService;

    @PostMapping("/FoodReview/insert")
    public String place(HttpServletRequest request) throws Exception {
        String content = request.getParameter("content");
        String id = (request.getParameter("id"));

        int score = Integer.parseInt(request.getParameter("score"));

        reviewService.register(content, score, Long.parseLong(id));
        
        return "redirect:/food/detail/"+id;
    }
}
