package reinfect.datalab.tour.http.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reinfect.datalab.tour.utilities.Weather;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Weather weather;

    @GetMapping("/")
    public String home(Model model) {
        String weatherData;

        try {
            weatherData = weather.weatherData();
        } catch (Exception exception) {
            weatherData = "{}";
        }

        model.addAttribute("weather", weatherData);
        return "_pages/public/home";
    }

}
