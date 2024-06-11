package datalab.reinfect.tour.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import datalab.reinfect.tour.entities.TourEntity;

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Integer> {
	
	Page<TourEntity> findAll(Pageable pageable);
	
	Page<TourEntity> findAllByPOSTSNContainingIgnoreCaseOrderByIdDesc(String postsn, Pageable pageable);
	Page<TourEntity> findAllByPOSTSJContainingIgnoreCaseOrderByIdDesc(String postsj, Pageable pageable);
	Page<TourEntity> findAllByADDRESSContainingIgnoreCaseOrderByIdDesc(String address, Pageable pageable);
	Page<TourEntity> findAllByNEWADDRESSContainingIgnoreCaseOrderByIdDesc(String newaddress, Pageable pageable);
	Page<TourEntity> findAllByTAGContainingIgnoreCaseOrderByIdDesc(String tag, Pageable pageable);
	Page<TourEntity> findAllByBFDESCContainingIgnoreCaseOrderByIdDesc(String bfdesc, Pageable pageable);

}
