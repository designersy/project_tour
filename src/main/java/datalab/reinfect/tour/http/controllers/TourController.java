package datalab.reinfect.tour.http.controllers;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import datalab.reinfect.tour.entities.TourEntity;
import datalab.reinfect.tour.services.TourService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import datalab.reinfect.tour.modules.TourModules;


@Controller
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController {
	
	@Autowired
	private TourService tourService;
    
	/* 수정 필요 @@@@@@@@@@@@@@
	@GetMapping("/")
	public String main(Model model) {
		
		if(!isTourData){
			return "tour/insertData";
		}
		
		// 내림차순으로 9개 표시
		List<TourEntity> tourListMain = tourService.별점순 정렬 함수();
		model.addAttribute("tourListMain", toruList);
		
		return "_pages/tour/tourMain";
	}
	*/
	
	
	// DB에 저장된 관광지 데이터를 출력하는 tourList 페이지로 연결해주는 라우터
	@GetMapping("/list")
	public String tourList(@RequestParam(value = "page", defaultValue="1") int page, Model model) {
		
		page -= 1;
		if (page > 0) {
		} else {
			page = 1;
		}
		
		Page<TourEntity> paging = tourService.getList(page);
		
		model.addAttribute("paging", paging);
		
		return "_pages/tour/tourList";
	}
	
    // 고유번호, 관광지명, 주소, 신주소, 태그, 장애인 편의시설
    // 총 6가지 옵션 중 하나를 선택하여 검색하는 기능을 제공하는 라우터
    @GetMapping("/search")
    public String tourSearch(@RequestParam(value = "criteria") String criteria,
    						@RequestParam(value = "keyword") String keyword,
    						@RequestParam(value = "page", defaultValue="1") int page,
    						Model model) 
    {
    	page -= 1;
    	
    	Page<TourEntity> paging = tourService.search(criteria, keyword, page);
    	model.addAttribute("criteria", criteria);
    	model.addAttribute("keyword", keyword);
    	model.addAttribute("paging", paging);
    	return "_pages/tour/tourSearch";
    }
	
	
	
	// 관광지의 자세한 정보를 보는 페이지로 연결해주는 라우터
	@GetMapping("/detail")
	public String tourDetail(@RequestParam(value = "id") int id, Model model) {
		TourEntity tourEntity = tourService.findById(id);
		
		model.addAttribute("tourEntity", tourEntity);
		
		return "_pages/tour/tourDetail";
	}
    
	
	
    // DB에 관광지 데이터를 추가하는 라우터
    @GetMapping("/insertData")
    public String insertData(Model model) {
    	List<TourEntity> tourEntity = tourService.findAll();
    	
    	if (tourEntity.isEmpty()) {
    		TourModules module = new TourModules();
        	List<String> stringList = module.tourApi(1000, 1968);
        	List<List<String>> dataList = module.dataKo(stringList);
        	tourService.saveList(dataList);
    	} else {
    		
    	}
    	return "_pages/tour/tourInsert";
    }
    
    
	
    // 지도를 그리고 관광지에 마커를 찍는 라우터
    @PostMapping("/map")
    public String tourMap() {
    	
    	return "_pages/tour/tourMap";
    }
  
    

    
    
    
    

}
