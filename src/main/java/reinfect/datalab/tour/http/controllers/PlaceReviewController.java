package reinfect.datalab.tour.http.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reinfect.datalab.tour.services.PlaceReviewService;
import reinfect.datalab.tour.services.PlaceService;

@Controller
@RequiredArgsConstructor
public class PlaceReviewController {

    private final PlaceReviewService reviewService;

    @PostMapping("/placeReview/insert")
    public String place(HttpServletRequest request) throws Exception {
        System.out.println("들어옴");
        String content = request.getParameter("content");
        int score = Integer.parseInt(request.getParameter("score"));
        String id = (request.getParameter("id"));

        reviewService.register(content, score, Long.parseLong(id));
        
        return "redirect:/place/detail?id="+id;
    }
}
