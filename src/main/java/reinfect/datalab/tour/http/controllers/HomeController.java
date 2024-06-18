package reinfect.datalab.tour.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reinfect.datalab.tour.enums.LatestType;
import reinfect.datalab.tour.services.BannerService;
import reinfect.datalab.tour.services.FoodService;
import reinfect.datalab.tour.services.PlaceService;
import reinfect.datalab.tour.utilities.Weather;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Weather weather;

    private final BannerService bannerService;
    private final PlaceService placeService;
    private final FoodService foodService;

    @GetMapping("/")
    public String home(Model model) {
        String weatherData;

        try {
            weatherData = weather.weatherData();
        } catch (Exception exception) {
            weatherData = "{}";
        }

        model.addAttribute("weather", weatherData);
        model.addAttribute("banners", bannerService.allItems());
        model.addAttribute("places", placeService.latest(LatestType.RANDOM));
        model.addAttribute("foods", foodService.latest(LatestType.RANDOM));
        return "_pages/public/home";
    }

}
