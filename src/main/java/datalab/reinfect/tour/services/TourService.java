package datalab.reinfect.tour.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import datalab.reinfect.tour.entities.TourEntity;
import datalab.reinfect.tour.repositories.TourRepository;

@Service
public class TourService {
	
	@Autowired
	private TourRepository tourRepository;
	
	public List<TourEntity> findAll(){
		List<TourEntity> tourList = tourRepository.findAll();
		return tourList;
	}

	public void saveList(List<List<String>> dataList) {
		for (int i = 0; i < dataList.size(); i++) {
			List<String> data = dataList.get(i);
			TourEntity tour = new TourEntity();
			tour.setPOSTSN(data.get(0));
			tour.setPOSTSJ(data.get(1));
			tour.setPOSTURL(data.get(2));
			tour.setADDRESS(data.get(3));
			tour.setNEWADDRESS(data.get(4));
			tour.setCMMNTELNO(data.get(5));
			tour.setCMMNFAX(data.get(6));
			tour.setCMMNHMPGURL(data.get(7));
			tour.setCMMNUSETIME(data.get(8));
			tour.setCMMNBSNDE(data.get(9));
			tour.setCMMNRSTDE(data.get(10));
			tour.setSUBWAYINFO(data.get(11));
			tour.setTAG(data.get(12));
			tour.setBFDESC(data.get(13));
			tour.setCreateDate(data.get(14));
			tour.setUpdateDate(data.get(15));
			
			tourRepository.save(tour);
		}
		
	}

	public Page<TourEntity> getList(int page) {
		int pagePerTourCount = 10;
		Pageable pageable = PageRequest.of(page,  pagePerTourCount, Sort.by(Sort.Direction.DESC, "id"));
		return tourRepository.findAll(pageable);
	}

	public TourEntity findById(int id) {
		Optional<TourEntity> op = tourRepository.findById(id);
		if (op.isPresent()) {
			TourEntity tour = op.get();
			return tour;
		} else {
			return null;
		}
	}
	
	public Page<TourEntity> search(String criteria, String keyword, int page){
		
		Page<TourEntity> tourList = null;
		int pagePerTourCount = 10;
		Pageable pageable = PageRequest.of(page,  pagePerTourCount, Sort.by(Sort.Direction.DESC, "id"));

		if (criteria.equals("고유번호")) {
			tourList = tourRepository.findAllByPOSTSNContainingIgnoreCaseOrderByIdDesc(keyword, pageable);
		} else if (criteria.equals("관광지명")) {
			tourList = tourRepository.findAllByPOSTSJContainingIgnoreCaseOrderByIdDesc(keyword, pageable);
		} else if (criteria.equals("주소")) {
			tourList = tourRepository.findAllByADDRESSContainingIgnoreCaseOrderByIdDesc(keyword, pageable);
		} else if (criteria.equals("신주소")) {
			tourList = tourRepository.findAllByNEWADDRESSContainingIgnoreCaseOrderByIdDesc(keyword, pageable);
		} else if (criteria.equals("태그")) {
			tourList = tourRepository.findAllByTAGContainingIgnoreCaseOrderByIdDesc(keyword, pageable);
		} else if (criteria.equals("장애인 편의시설")) {
			tourList = tourRepository.findAllByBFDESCContainingIgnoreCaseOrderByIdDesc(keyword, pageable);
		}
		
		return tourList;
	}

}
