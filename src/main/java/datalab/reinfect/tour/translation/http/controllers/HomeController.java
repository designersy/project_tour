package reinfect.datalab.translation.http.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import reinfect.datalab.translation.utilities.Translate;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final Translate translate;
	
	@GetMapping("/")
	public String home() {
		translate.work();
		return "_pages/home";
	}
	
}